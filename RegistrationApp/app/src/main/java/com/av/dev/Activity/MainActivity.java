package com.av.dev.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.av.dev.Core.AppController;
import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.RDialog;
import com.av.dev.Core.REngine;
import com.av.dev.Core.RSharedPreferences;
import com.av.dev.Core.RSingleton;
import com.av.dev.Fragment.HomeFragment;
import com.av.dev.Fragment.SettingsFragment;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    public static MainActivity INSTANCE = null;

    @BindView(R.id.app_bar)
    Toolbar toolbar;
    @BindView(R.id.toolbartext)
    TextView toolbarText;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        INSTANCE = this;

        setFrameLayout(R.id.framelayout_main);

        initializeToolbar();
        addBottomToolbar();
        REngine.switchFragment(this, new HomeFragment(), getFrameLayout());

    }

    public void initializeToolbar(){
        toolbar.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
        toolbarText.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

//        setSupportActionBar(toolbar);
//
//
//        if (RSingleton.getCurrentFragment().equalsIgnoreCase("home")){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setDisplayShowHomeEnabled(false);
//        } else {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }

    }

    private void addBottomToolbar(){

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.home, R.color.colorTextColor);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Setting", R.drawable.gear, R.color.colorTextColor);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Log out", R.drawable.logout, R.color.colorTextColor);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedColorBackGround)));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Enable the translation of the FloatingActionButton
//        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedTextColor)));
        bottomNavigation.setInactiveColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));


        // Display color under navigation bar (API 21+)
        // Don't forget these lines in your style-v21
        // <item name="android:windowTranslucentNavigation">true</item>
        // <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(false);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);


        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...

                if (position == 1){
//                    RDialog.showChooseColorLogin(INSTANCE,"home");
                    REngine.switchFragment(INSTANCE, new SettingsFragment(), getFrameLayout());

                } else if (position == 2){
                    RSharedPreferences.clearAllPreferences();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                } else if (position == 0){
                    REngine.switchFragment(INSTANCE, new HomeFragment(), getFrameLayout());
                }

                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }

    public void changeHomeLayout(){
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedColorBackGround)));
        bottomNavigation.setAccentColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedTextColor)));
        bottomNavigation.setInactiveColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
    }
}
