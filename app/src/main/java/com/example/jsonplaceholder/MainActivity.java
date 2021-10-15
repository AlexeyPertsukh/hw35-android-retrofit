package com.example.jsonplaceholder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import jsonplaceholder_class.User;
import network.Api;
import network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.IConst;
import utils.ILog;
import utils.IToast;
import utils.LocalUserStorage;

/*
https://jsonplaceholder.typicode.com/users
На одном фрагменте список имен(RecyclerView).
На втором фрагменте детальная информация(почта, адрес(улица и suite), телефон, название компании).

Поиск решения с updateTvInfo и динаническим фрагментом
 */

public class MainActivity extends AppCompatActivity implements ISendToFragment, IConst, IToast, ILog {

    private static final boolean USER_INFO_INVISIBLE = false;
    private static final boolean SHOW_MESSAGE_BY_LOAD_USERS = true;
    private static final boolean NOT_SHOW_MESSAGE_BY_LOAD_USERS = false;

    private Api api;

    private UserFragment userFragment;
    private UserInfoFragment userInfoFragment;

    private FragmentContainerView fcUsers;
    private FragmentContainerView fcUserInfo;

    private ArrayList<User> users;

    private State state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        boolean userInfoIsVisible = false;

        if (savedInstanceState != null) {
            users = (ArrayList<User>) savedInstanceState.getSerializable(KEY_USERS);
            userInfoIsVisible = (Boolean) savedInstanceState.getBoolean(KEY_USER_INFO_IS_VISIBLE);
            printLog("MainActivity - onCreate - read Bundle: \n" + users);
        }

        api = NetworkService.getInstance().getJsonApi();

        initViews();
        initFragments();

        state = getState(userInfoIsVisible);

        if(users == null) {
            readUsersFromNet(NOT_SHOW_MESSAGE_BY_LOAD_USERS);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_read_from_server) {
            readUsersFromNet(SHOW_MESSAGE_BY_LOAD_USERS);
        } else if(id == R.id.menu_git) {
            Intent Browse = new Intent(Intent.ACTION_VIEW, Uri.parse(GIT));
            startActivity(Browse);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean usersReading;
    private void readUsersFromNet(boolean showMessageByReload) {
        if(usersReading) {
            return;
        }
        usersReading = true;
        api.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                printLog("MainActivity - readUsersFromNet");
                users = new ArrayList<>(response.body());
                if(!users.isEmpty()) {
                    users.addAll(LocalUserStorage.getTestUsers());
                    userFragment.updateUsers(users);
                    state = getState(USER_INFO_INVISIBLE);
                    if(showMessageByReload) {
                        shortToast(getApplicationContext(), MESSAGE_DATA_LOADED);
                    }
                }
                usersReading = false;
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                longToast(getApplicationContext(), MESSAGE_LOAD_FAILED + " \n" + BASE_URL);
                usersReading = false;
            }
        });
    }

    private State getState(boolean userInfoIsVisible) {
        if(getResources().getConfiguration().orientation == ORIENTATION_VERTICAL) {
            return new StateVertical(userInfoIsVisible);
        } else {
            return new StateHorizontal(userInfoIsVisible);
        }
    }

    void initFragments() {
        initUserFragment();
        initUserInfoFragment();
    }

    private void initUserFragment() {
        FragmentManager fm = getSupportFragmentManager();

        userFragment = (UserFragment) fm.findFragmentById(R.id.fcUsers);
        if(userFragment == null) {
            userFragment = new UserFragment();
            fm.beginTransaction()
                    .add(R.id.fcUsers, userFragment)
                    .commit();
        }
    }

    private void initUserInfoFragment() {
        FragmentManager fm = getSupportFragmentManager();

        userInfoFragment = (UserInfoFragment)fm.findFragmentById(R.id.fcUserInfo);
        if(userInfoFragment == null) {
            userInfoFragment = new UserInfoFragment();
            fm.beginTransaction()
                    .add(R.id.fcUserInfo, userInfoFragment)
                    .commit();
        }
    }

    private void initViews() {
        fcUsers = findViewById(R.id.fcUsers);
        fcUserInfo = findViewById(R.id.fcUserInfo);
    }

    @Override
    public void setUserInfo(User user, int resId) {
        state.updateUserInfo(user, resId);
    }

    @Override
    public void onBackPressed() {
        state.onBackPressed();
    }

    //сохранение
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_USERS, users);
        boolean vis = fcUserInfo.getVisibility() == View.VISIBLE;
        outState.putBoolean(KEY_USER_INFO_IS_VISIBLE, vis);

        printLog("MainActivity - onSaveInstanceState - save Bundle: \n" + users);
    }

    private void showEndDialog() {
        @SuppressLint("DefaultLocale")
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .create();
        alertDialog.setMessage("Вы действительно хотите выйти из программы?");
        alertDialog.setTitle("Выход из программы");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.setContentView(R.layout.support_simple_spinner_dropdown_item);
        alertDialog.show();
    }

    //Паттерн State: Действия в зависимости от состояния экрана(ориентации)
    public abstract class State {

        public State(boolean userInfoIsVisible) {
            fcUsers.setVisibility(View.VISIBLE);
            setVisibleUserInfo(userInfoIsVisible);
        }

        abstract void updateUserInfo(User user, int resId);

        void onBackPressed() {
            if(fcUserInfo.getVisibility() == View.VISIBLE) {
                fcUserInfo.setVisibility(View.GONE);
                fcUsers.setVisibility(View.VISIBLE);
            } else {
                showEndDialog();
            }
        }

        void setVisibleUserInfo(boolean userInfoIsVisible) {
            if(userInfoIsVisible) {
                fcUserInfo.setVisibility(View.VISIBLE);
            } else {
                fcUserInfo.setVisibility(View.GONE);
            }
        }
    }

    class StateVertical extends State {

        public StateVertical(boolean userInfoIsVisible) {
            super(userInfoIsVisible);
            if(!userInfoIsVisible) {
                fcUsers.setVisibility(View.VISIBLE);
            } else {
                fcUsers.setVisibility(View.GONE);
            }
        }

        @Override
        void updateUserInfo(User user, int resId) {
            fcUsers.setVisibility(View.GONE);
            fcUserInfo.setVisibility(View.VISIBLE);
            userInfoFragment.setInfo(user, resId);
        }
    }

    class StateHorizontal extends State {
        public StateHorizontal(boolean userInfoIsVisible) {
            super(userInfoIsVisible);
            fcUsers.setVisibility(View.VISIBLE);
        }

        @Override
        void updateUserInfo(User user, int resId) {
            fcUserInfo.setVisibility(View.VISIBLE);
            userInfoFragment.setInfo(user, resId);
        }
    }

}