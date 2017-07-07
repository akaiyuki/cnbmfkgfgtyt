package com.av.dev.Fragment;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.Api.ApiResponseBook;
import com.av.dev.Api.ApiResponseHotel;
import com.av.dev.Api.ApiResponseHotelDetails;
import com.av.dev.Api.RestClient;
import com.av.dev.Core.AppController;
import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.RDialog;
import com.av.dev.Core.REngine;
import com.av.dev.Core.RSharedPreferences;
import com.av.dev.Core.RSingleton;
import com.av.dev.Core.StatusData;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;
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
public class BookDetailsFragment extends Fragment {

    public static BookDetailsFragment INSTANCE =null;
    private ProgressDialog progressDialog;

    @BindView(R.id.imgbanner)
    ImageView imgBanner;
    @BindView(R.id.imgprofile)
    ImageView imgProfile;
    @BindView(R.id.txtname)
    TextView txtName;
    @BindView(R.id.txtaddress)
    TextView txtAddress;
    @BindView(R.id.txtcontact)
    TextView txtContact;
    @BindView(R.id.txtstar)
    TextView txtStar;
    @BindView(R.id.txt_desc_title)
    TextView txtDescTitle;
    @BindView(R.id.txtdesc)
    TextView txtDesc;
    @BindView(R.id.txt_am_title)
    TextView txtAmenityTitle;
    @BindView(R.id.txtam)
    TextView txtAmenity;
    @BindView(R.id.btnbook)
    Button btnBook;
    @BindView(R.id.relbackground)
    RelativeLayout relativeLayout;



    public BookDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_details, container, false);
        ButterKnife.bind(this, view);
        INSTANCE = this;
        progressDialog = new ProgressDialog(getActivity());

        populateViews();
        RSingleton.setCurrentFragment("book");


        return view;
    }

    /* setting fragment ui */
    public void populateViews(){
        txtName.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtAddress.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtContact.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtStar.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtDescTitle.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtDesc.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtAmenityTitle.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtAmenity.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        btnBook.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
        btnBook.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        relativeLayout.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));

        requestApiGetHotelDetails();

    }

    /* api request for getting hotel details with params hotel id and check data */
    private void requestApiGetHotelDetails(){

            progressDialog.show();

            RestClient restClient = new RestClient(RestClient.bookApiService);
            Call<ApiResponseHotelDetails> call = restClient.getApiServiceBook().getHotelDetails(RSingleton.getHotelId(),RSingleton.getCheckin(),RSingleton.getCheckout());

            call.enqueue(new Callback<ApiResponseHotelDetails>() {
                @Override
                public void onResponse(Call<ApiResponseHotelDetails> call, Response<ApiResponseHotelDetails> response) {

                    progressDialog.dismiss();

                    if (response.isSuccessful()){

//                        Log.d("api success", response.body().getMsg()+" "+RSingleton.getHotelId());
//
                        txtName.setText(response.body().getHotelDetailObject().getHotelName());
                        txtAddress.setText(response.body().getHotelDetailObject().getAddress());
                        txtContact.setText(response.body().getHotelDetailObject().getContact());
                        txtStar.setText(response.body().getHotelDetailObject().getStar()+"-star hotel");
                        txtDesc.setText(response.body().getHotelDetailObject().getDescription());
                        txtAmenity.setText(response.body().getHotelDetailObject().getAmenities());

                        Picasso.with(AppController.getInstance())
                                .load(response.body().getHotelDetailObject().getImgBanner())
                                .fit()
                                .into(imgBanner);

                        Picasso.with(AppController.getInstance())
                                .load(response.body().getHotelDetailObject().getImgProfile())
                                .fit()
                                .into(imgProfile);

                        RSingleton.setSelectedHotel(response.body().getHotelDetailObject().getHotelName());

                        if (response.body().getHotelDetailObject().getStatus().equalsIgnoreCase(StatusData.STATUS_UNAVAILABLE)){
                            btnBook.setEnabled(false);
                            btnBook.setText("Booking Unavailable");
                            btnBook.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
                        } else {
                            btnBook.setEnabled(true);
                            btnBook.setText("BOOK");
                            btnBook.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
                        }
                    }

                }

                @Override
                public void onFailure(Call<ApiResponseHotelDetails> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });

    }

    @OnClick(R.id.btnbook)
    public void onClickBook(){

        if (btnBook.getText().toString().equalsIgnoreCase("book")){
            requestApiBookUser();
        }

    }

    /* book user api */
    private void requestApiBookUser(){

        progressDialog.show();

        RestClient restClient = new RestClient(RestClient.bookApiService);
        Call<ApiResponseBook> call = restClient.getApiServiceBook().bookUser(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userId),
                RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.firstName),
                RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.lastName),
                RSingleton.getSelectedHotel(),RSingleton.getCheckin(),RSingleton.getCheckout());

        call.enqueue(new Callback<ApiResponseBook>() {
            @Override
            public void onResponse(Call<ApiResponseBook> call, Response<ApiResponseBook> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    RDialog.showSuccessDialog((BaseActivity) getActivity(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponseBook> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


    }


}
