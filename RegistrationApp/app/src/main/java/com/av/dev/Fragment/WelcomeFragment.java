package com.av.dev.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.Activity.MainActivity;
import com.av.dev.Api.ApiResponseUser;
import com.av.dev.Api.RestClient;
import com.av.dev.Core.AppController;
import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.RDialog;
import com.av.dev.Core.REngine;
import com.av.dev.Core.RSharedPreferences;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    private ProgressDialog progressDialog;

    @BindView(R.id.slider)
    SliderLayout mDemoSlider;
    @BindView(R.id.btnchange)
    Button btnChangeColor;
    @BindView(R.id.btnlogin)
    Button btnLogin;
    @BindView(R.id.btnsign)
    TextView btnSignup;
    @BindView(R.id.txttitle)
    TextView txtTitle;
    @BindView(R.id.editusername)
    EditText editUsername;
    @BindView(R.id.editpassword)
    EditText editPassword;


    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);

        setUi();

        progressDialog = new ProgressDialog(getActivity());

        return view;
    }


    @OnClick(R.id.btnlogin)
    public void onClickLogin(){

        if (editUsername.getText().length() != 0 && editPassword.getText().length() != 0){
            requestApiLoginUser(editUsername.getText().toString(), editPassword.getText().toString());
        }

    }

    @OnClick(R.id.btnsign)
    public void onClickSignUp(){
        REngine.switchFragment((BaseActivity) getActivity(), new SignUpFragment(), ((BaseActivity)getActivity()).getFrameLayout());
    }

    public void setUi(){
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Reservation", "http://searchengineland.com/figz/wp-content/seloads/2014/04/hotel-bell-customer-service-ss-1920-800x450.jpg");
        url_maps.put("Booking", "http://dungcukhachsan.vn/upload/hinhanh/685963601086895.jpg");
        url_maps.put("Hotel", "https://securitysurveillance.ca/wp-content/uploads/2016/11/hotel-reception-phone.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Reservation",R.drawable.home);
        file_maps.put("Booking",R.drawable.gear);
        file_maps.put("Hotel",R.drawable.logout);

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
//            .image(file_maps.get(name))

            textSliderView
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
    }

    private void requestApiLoginUser(String username, String password){

        progressDialog.show();

        RestClient restClient = new RestClient(RestClient.userApiService);
        Call<ApiResponseUser> call = restClient.getApiServiceUser().userLogin(username,password);
        call.enqueue(new Callback<ApiResponseUser>() {
            @Override
            public void onResponse(Call<ApiResponseUser> call, Response<ApiResponseUser> response) {

                progressDialog.dismiss();

                if (response.isSuccessful()){
                    Log.d("response", response.body().getMessage());

                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround,response.body().getData().getBackgroundColor());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor,response.body().getData().getTextColor());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground, response.body().getData().getLayoutColor());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayout, response.body().getData().getLayout());

                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userId, response.body().getData().getUserId());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.firstName, response.body().getData().getFirstName());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.lastName, response.body().getData().getLastName());

                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }

            }

            @Override
            public void onFailure(Call<ApiResponseUser> call, Throwable t) {
//                Log.d("api response", t.getMessage());

                progressDialog.dismiss();


            }
        });
    }



}
