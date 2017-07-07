package com.av.dev.Fragment;


import android.app.ProgressDialog;
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

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.av.dev.Activity.LoginActivity;
import com.av.dev.Api.ApiResponseHotel;
import com.av.dev.Api.RestClient;
import com.av.dev.Core.AppController;
import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.RDateCheckin;
import com.av.dev.Core.RDateCheckout;
import com.av.dev.Core.RDatepicker;
import com.av.dev.Core.RDialog;
import com.av.dev.Core.REngine;
import com.av.dev.Core.RSharedPreferences;
import com.av.dev.Core.RSingleton;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static HomeFragment INSTANCE = null;
    private ProgressDialog progressDialog;
    @BindView(R.id.editlocation)
    EditText editLocation;
    @BindView(R.id.editcheckin)
    EditText editCheckin;
    @BindView(R.id.editcheckout)
    EditText editCheckout;
    @BindView(R.id.btnsearch)
    Button btnSearch;
    @BindView(R.id.imgbackground)
    ImageView imgBackground;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        INSTANCE = this;
        progressDialog = new ProgressDialog(getActivity());

        populateViews();
        RSingleton.setCurrentFragment("home");
        RSingleton.setCurrentView(view);

        editCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RDateCheckin datePicker1 = new RDateCheckin((BaseActivity) getActivity(), (EditText)view);

            }
        });

        editCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RDateCheckout datePicker2 = new RDateCheckout((BaseActivity) getActivity(), (EditText)view);

            }
        });

        return view;
    }

    public void populateViews(){
        btnSearch.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
        editLocation.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        editCheckin.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        editCheckout.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        editLocation.setHintTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        editCheckin.setHintTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        editCheckout.setHintTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        btnSearch.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        Picasso
                .with(getActivity())
                .load("https://apptha-blog.s3.amazonaws.com/blog/wp-content/uploads/2014/11/banner.jpg")
                .fit()
                .into(imgBackground);

    }

    @OnClick(R.id.btnsearch)
    public void onClickSearch(){


        if (editLocation.getText().length() != 0 && editCheckin.getText().length() != 0
                && editCheckout.getText().length() != 0){

            RSingleton.setLocation(editLocation.getText().toString());
            requestApiGetHotel(editLocation.getText().toString());

        } else {
            RDialog.showSuccessDialog((BaseActivity) getActivity(), "Please input necessary details");
        }

    }


    private void requestApiGetHotel(String location){

        progressDialog.show();

        RestClient restClient = new RestClient(RestClient.bookApiService);
        Call<ApiResponseHotel> call = restClient.getApiServiceBook().getHotel(location);
        call.enqueue(new Callback<ApiResponseHotel>() {
            @Override
            public void onResponse(Call<ApiResponseHotel> call, Response<ApiResponseHotel> response) {

                progressDialog.dismiss();
                if (response.isSuccessful()){
                    Log.d("get hotel", response.body().getMsg());

                    RSingleton.setResultHotels(response.body().getHotelObject());
                    REngine.switchFragment((BaseActivity) getActivity(), new SearchResultFragment(), ((BaseActivity)getActivity()).getFrameLayout());

                }

            }

            @Override
            public void onFailure(Call<ApiResponseHotel> call, Throwable t) {

//                Log.d("api error", t.getMessage());
                progressDialog.dismiss();

            }
        });
    }



}
