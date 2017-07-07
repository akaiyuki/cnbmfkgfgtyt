package com.av.dev.Core;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.av.dev.Activity.MainActivity;
import com.av.dev.Api.ApiResponse;
import com.av.dev.Api.ApiResponseUser;
import com.av.dev.Api.RestClient;
import com.av.dev.Fragment.BookDetailsFragment;
import com.av.dev.Fragment.HomeFragment;
import com.av.dev.Fragment.LoginFragment;
import com.av.dev.Fragment.SearchResultFragment;
import com.av.dev.Fragment.SettingsFragment;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by CodeSyaona on 03/07/2017.
 */

public class REngine {

    public static void switchFragment(BaseActivity baseActivity, Fragment fragment, int frame) {

        FragmentManager fm = baseActivity.getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(frame, fragment);
        transaction.addToBackStack(fragment.getClass().toString());
        transaction.commit();
    }

//    api call for selecting color
    public static void requestApiSelectColor(String color, final String fragment, final ProgressDialog progressDialog){

        progressDialog.show();

        RestClient restClient = new RestClient(RestClient.colorApiService);
        Call<ApiResponse> call = restClient.getApiServiceColor().selectedColor(color);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                progressDialog.dismiss();

                if (response.isSuccessful()){
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround,response.body().getData().getBackgroundColor());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor,response.body().getData().getTextColor());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground, response.body().getData().getLayoutBackground());

                    /* change ui color based on selected color */
                    if (fragment.equalsIgnoreCase("login")){
                        LoginFragment.INSTANCE.changeLayout();
                    } else if (fragment.equalsIgnoreCase("home")){
                        MainActivity.INSTANCE.changeHomeLayout();
                        MainActivity.INSTANCE.initializeToolbar();

                        if (RSingleton.getCurrentFragment().equalsIgnoreCase("home")){
                            HomeFragment.INSTANCE.populateViews();
                            SettingsFragment.INSTANCE.initDisplayViews();
                        } else if (RSingleton.getCurrentFragment().equalsIgnoreCase("search")){
                            HomeFragment.INSTANCE.populateViews();
                            SearchResultFragment.INSTANCE.initDisplayViews();
                            SettingsFragment.INSTANCE.initDisplayViews();
                        } else if (RSingleton.getCurrentFragment().equalsIgnoreCase("book")){
                            HomeFragment.INSTANCE.populateViews();
                            SearchResultFragment.INSTANCE.initDisplayViews();
                            BookDetailsFragment.INSTANCE.populateViews();
                            SettingsFragment.INSTANCE.initDisplayViews();
                        }


                    }

                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                Log.d("api response", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

//    api call for update settings
    public static void requestApiUpdateSettings(final BaseActivity baseActivity, final ProgressDialog progressDialog){

        progressDialog.show();

        RestClient restClient = new RestClient(RestClient.userApiService);
        Call<ApiResponseUser> call = restClient.getApiServiceUser().updateSettings(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userId),
                RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedTextColor),
                RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround),
                RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground),
                RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayout));

        call.enqueue(new Callback<ApiResponseUser>() {
            @Override
            public void onResponse(Call<ApiResponseUser> call, Response<ApiResponseUser> response) {

                progressDialog.dismiss();
                if (response.isSuccessful()){
                    RDialog.showSuccessDialog(baseActivity, response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ApiResponseUser> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }



}
