package com.av.dev.Activity;

import android.os.Bundle;

import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.REngine;
import com.av.dev.Fragment.LoginFragment;
import com.av.dev.Fragment.WelcomeFragment;
import com.av.dev.R;

public class LoginActivity extends BaseActivity {

    public static LoginActivity INSTANCE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        setFrameLayout(R.id.framelayout_main);

        INSTANCE = this;
        REngine.switchFragment(this, new WelcomeFragment(), getFrameLayout());


    }
}
