package com.av.dev.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.Activity.MainActivity;
import com.av.dev.Api.ApiResponse;
import com.av.dev.Api.RestClient;
import com.av.dev.Core.AppController;
import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.RDialog;
import com.av.dev.Core.REngine;
import com.av.dev.Core.RSharedPreferences;
import com.av.dev.Core.RSingleton;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;
import com.av.dev.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public static LoginFragment INSTANCE = null;

    @BindView(R.id.btnchange)
    Button btnChangeColor;
    @BindView(R.id.btnlogin)
    Button btnLogin;
    @BindView(R.id.btnsign)
    Button btnSignup;
    @BindView(R.id.layoutbackground)
    RelativeLayout layoutBackground;
    @BindView(R.id.txttitle)
    TextView txtTitle;
    @BindView(R.id.editusername)
    EditText editUsername;
    @BindView(R.id.editpassword)
    EditText editPassword;
    @BindView(R.id.imgsetting)
    ImageView imgSetting;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        INSTANCE = this;

        changeLayout();

        return view;
    }

    @OnClick(R.id.imgsetting)
    public void onClickChange(){
        RDialog.showChooseColorLogin((BaseActivity) getActivity(), "login");
    }

    @OnClick(R.id.btnlogin)
    public void onClickLogin(){
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    public void changeLayout(){
        btnChangeColor.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedColorBackGround)));
        btnLogin.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
        btnSignup.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));

        btnChangeColor.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedTextColor)));
        btnSignup.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedTextColor)));
        btnLogin.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        layoutBackground.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedLayoutBackground)));
        txtTitle.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        editUsername.setHintTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        editPassword.setHintTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        editUsername.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        editPassword.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
    }



}
