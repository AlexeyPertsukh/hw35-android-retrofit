package com.example.jsonplaceholder;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

import jsonplaceholder_class.User;
import utils.IConst;
import utils.ILog;

public class UserFragment extends Fragment implements Serializable, IConst, ILog {

    private RecyclerView rvUsers;
    private ArrayList<User> users;
    private UserAdapter userAdapter;
    private ISendToFragment iSendToFragment;


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        iSendToFragment = (ISendToFragment) context;
    }


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users = new ArrayList<>();
        printLog("#UserFragment - onCREATE: " + this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        printLog("#UserFragment - onCreateVIEW");

        if(savedInstanceState != null) {
            users = (ArrayList<User>) savedInstanceState.getSerializable(KEY_USERS);
            printLog("#UserFragment - onCreateVIEW - read from Bundle: " + users);
        }

        initViews(view);
        initRvUsers();
        setAdapter();
        initListeners();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_USERS, users);
    }


    private void initViews(View view) {
        rvUsers = view.findViewById(R.id.rvUsers);
    }

    private void initListeners() {
        userAdapter.setOnClickItem(this::clickUserItem);
    }

    private void back(View view) {

    }

    private void clickUserItem(User user, int resId) {
       iSendToFragment.setUserInfo(user, resId);
    }


    private void initRvUsers() {
        rvUsers.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        userAdapter = new UserAdapter(users);
        rvUsers.setLayoutManager(layoutManager);
        rvUsers.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void setAdapter() {
        rvUsers.setAdapter(userAdapter);
        printLog("#UserFragment - setAdapter");
    }

    public void updateUsers(ArrayList<User> us) {
        users.clear();
        users.addAll(us);
        userAdapter.notifyDataSetChanged();
        printLog("#UserFragment - updateUsers");
    }

}