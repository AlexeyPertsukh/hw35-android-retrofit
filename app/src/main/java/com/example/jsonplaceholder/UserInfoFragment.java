package com.example.jsonplaceholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import jsonplaceholder_class.User;
import utils.IConst;
import utils.ILog;
import utils.IToast;


public class UserInfoFragment extends Fragment implements Serializable, IConst, ILog, IToast {
    private static final boolean STATE_VISIBLE = true;
    private static final boolean STATE_INVISIBLE = false;

    private TextView tvInfoName;
    private TextView tvInfoUsername;
    private TextView tvInfoEmail;
    private TextView tvInfoPhone;
    private TextView tvInfoWebsite;
    private TextView tvInfoAvatar;

    private TextView tvInfoAddressCity;
    private TextView tvInfoAddressStreet;
    private TextView tvInfoAddressSuite;
    private TextView tvInfoAddressZipcode;
    private TextView tvInfoAddressGeo;

    private TextView tvInfoCompanyName;
    private TextView tvInfoCompanyCatchPhrase;
    private TextView tvInfoCompanyBs;

    private TextView tvHeaderAddress;
    private TextView tvHeaderCompany;

    private ConstraintLayout clAddress;
    private ConstraintLayout clCompany;

    private User user;
    private int resId;
    private Config config;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printLog("*UserInfoFragment - onCREATE: " + this);
        config = new Config();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        printLog("*UserInfoFragment - onCreateVIEW");
        if(savedInstanceState != null) {
            user = (User)savedInstanceState.getSerializable(KEY_CURRENT_USER);
            resId = savedInstanceState.getInt(KEY_RESOURCE_ID);
            config = (Config) savedInstanceState.getSerializable(KEY_USER_INFO_CONFIG);
            printLog("*UserInfoFragment - onCreateVIEW - read from Bundle: " + user + " " + resId);
        }

        initViews(view);
        initListeners();
        setVisibleConstraintLayouts(config);
        if(user != null) {
            setInfo(user, resId);
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_CURRENT_USER, user);
        outState.putInt(KEY_RESOURCE_ID, resId);

        Config config = new Config();
        config.addressVisible = Config.codeVisibleToBoolean(clAddress.getVisibility());
        config.companyVisible = Config.codeVisibleToBoolean(clCompany.getVisibility());
        outState.putSerializable(KEY_USER_INFO_CONFIG, config);
    }

    private void initViews(View view) {
        tvInfoName = view.findViewById(R.id.tvInfoCity);
        tvInfoUsername = view.findViewById(R.id.tvInfoStreet);
        tvInfoEmail = view.findViewById(R.id.tvInfoSuite);
        tvInfoPhone = view.findViewById(R.id.tvInfoZipcode);
        tvInfoWebsite = view.findViewById(R.id.tvInfoGeo);
        tvInfoAvatar = view.findViewById(R.id.tvInfoAvatar);

        //Address
        tvInfoAddressCity = view.findViewById(R.id.tvInfoAddressCity);
        tvInfoAddressStreet = view.findViewById(R.id.tvInfoAddressStreet);
        tvInfoAddressSuite = view.findViewById(R.id.tvInfoAddressSuite);
        tvInfoAddressZipcode = view.findViewById(R.id.tvInfoAddressZipcode);
        tvInfoAddressGeo = view.findViewById(R.id.tvInfoAddressGeo);

        //Company
        tvInfoCompanyName = view.findViewById(R.id.tvInfoCompanyName);
        tvInfoCompanyCatchPhrase = view.findViewById(R.id.tvInfoCompanyCatchPhrase);
        tvInfoCompanyBs = view.findViewById(R.id.tvInfoCompanyBs);

        //
        tvHeaderAddress = view.findViewById(R.id.tvHeaderAddress);
        tvHeaderCompany = view.findViewById(R.id.tvHeaderCompany);

        //
        clAddress = view.findViewById(R.id.clAddress);
        clCompany = view.findViewById(R.id.clCompany);
    }

    private void initListeners() {
        tvHeaderAddress.setOnClickListener(this::selectShowAddress);
        tvHeaderCompany.setOnClickListener(this::selectShowCompany);
    }

    private void selectShowCompany(View view) {
        boolean visible = false;
        if(clCompany.getVisibility() == View.VISIBLE) {
            visible = true;
        }
        setVisibleDetailsCompanyAndSetText(!visible);
    }

    private void selectShowAddress(View view) {
        boolean visible = false;
        if(clAddress.getVisibility() == View.VISIBLE) {
            visible = true;
        }
        setVisibleDetailsAddressAndSetText(!visible);
    }

    private void setVisibleDetailsAddressAndSetText(boolean state) {
        setVisibleLayoutAndSetText(tvHeaderAddress, clAddress, state, "address");
    }

    private void setVisibleDetailsCompanyAndSetText(boolean state) {
        setVisibleLayoutAndSetText(tvHeaderCompany, clCompany, state, "company");
    }

    private void setVisibleLayoutAndSetText(TextView tv, ConstraintLayout cl, boolean state, String text) {
        if (state) {
            tv.setText("- " + text);
            cl.setVisibility(View.VISIBLE);
        } else {
            tv.setText("+ " + text);
            cl.setVisibility(View.GONE);
        }
    }

    @SuppressLint("SetTextI18n")
    public void setInfo(User user, int resId) {
        printLog("UserInfoFragment - setInfo: " + user +", "+resId);
        this.user = user;
        this.resId = resId;

        tvInfoName.setText(user.getName());
        tvInfoUsername.setText(user.getUsername());
        tvInfoEmail.setText(user.getEmail());
        tvInfoPhone.setText(user.getPhone());
        tvInfoWebsite.setText(user.getWebsite());

        //Address
        tvInfoAddressCity.setText(user.getAddress().getCity());
        tvInfoAddressStreet.setText(user.getAddress().getStreet());
        tvInfoAddressSuite.setText(user.getAddress().getSuite());
        tvInfoAddressZipcode.setText(user.getAddress().getZipcode());
        tvInfoAddressGeo.setText(user.getAddress().getGeoString());

        //Company
        tvInfoCompanyName.setText(user.getCompany().getName());
        tvInfoCompanyCatchPhrase.setText(user.getCompany().getCatchPhrase());
        tvInfoCompanyBs.setText(user.getCompany().getBs());

        if(user.getName() != null && !user.getName().isEmpty()) {
            String letter = String.valueOf(user.getName().charAt(0));
            tvInfoAvatar.setText(letter);
        }
        tvInfoAvatar.setBackgroundResource(resId);

        printLog("*UserInfoFragment - setInfo");
    }

    public User getUser() {
        return user;
    }

    private void setVisibleConstraintLayouts(Config config) {
        setVisibleDetailsAddressAndSetText(config.addressVisible);
        setVisibleDetailsCompanyAndSetText(config.companyVisible);
    }

    public boolean isEmptyUser() {
        return user == null;
    }

    //класс для сохранения конфигурации отображения при повороте
    private static class Config implements Serializable{
        private static final boolean DEFAULT_STATE_VISIBLE = STATE_VISIBLE;
        public boolean addressVisible;
        public boolean companyVisible;

        public Config() {
            this.addressVisible = DEFAULT_STATE_VISIBLE;
            this.companyVisible = DEFAULT_STATE_VISIBLE;
        }

        static boolean codeVisibleToBoolean(int code) {
            return code == View.VISIBLE;
        }
    }
}