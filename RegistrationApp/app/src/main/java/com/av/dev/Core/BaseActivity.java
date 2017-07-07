package com.av.dev.Core;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by CodeSyaona on 03/07/2017.
 */

public class BaseActivity extends AppCompatActivity {

    public static BaseActivity INSTANCE;

    private int mFrameLayout;

    public BaseActivity() {
        INSTANCE = this;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            backHandling();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        backHandling();

    }

    public void backHandling() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    public void setFrameLayout(int resId) {
        this.mFrameLayout = resId;
    }

    public int getFrameLayout() {
        return this.mFrameLayout;
    }

}
