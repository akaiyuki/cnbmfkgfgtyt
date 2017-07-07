package com.av.dev.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.Adapter.HotelGridAdapter;
import com.av.dev.Adapter.HotelListAdapter;
import com.av.dev.Api.ApiResponseHotel;
import com.av.dev.Api.RestClient;
import com.av.dev.Core.AppController;
import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.REngine;
import com.av.dev.Core.RSharedPreferences;
import com.av.dev.Core.RSingleton;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    public static SearchResultFragment INSTANCE = null;

    @BindView(R.id.lineartop)
    LinearLayout linearTop;
    @BindView(R.id.txt_location)
    TextView txtLocation;
    @BindView(R.id.txt_location_value)
    TextView txtLocationValue;
    @BindView(R.id.txt_checkin)
    TextView txtCheckin;
    @BindView(R.id.txt_checkin_value)
    TextView txtCheckinValue;
    @BindView(R.id.txt_checkout)
    TextView txtCheckout;
    @BindView(R.id.txt_checkout_value)
    TextView txtCheckoutValue;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.relative)
    RelativeLayout relativeLayout;


    public SearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.bind(this, view);

        INSTANCE = this;

        initDisplayViews();
        RSingleton.setCurrentFragment("search");
        txtLocationValue.setText(RSingleton.getLocation());

        if (RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayout).equalsIgnoreCase("gridview")){
            initGridDisplay();
        } else {
            initListDisplay();
        }

        return view;
    }

    // Display a list
    private void initListDisplay(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        HotelListAdapter mAdapter = new HotelListAdapter(RSingleton.getResultHotels());
        recyclerView.setAdapter(mAdapter);

    }

    // Display the Grid
    private void initGridDisplay(){
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        HotelGridAdapter mAdapter = new HotelGridAdapter(RSingleton.getResultHotels());
        recyclerView.setAdapter(mAdapter);
    }

    public void initDisplayViews(){
        linearTop.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
        txtLocation.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtLocationValue.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtCheckin.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtCheckinValue.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtCheckout.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtCheckoutValue.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        txtCheckinValue.setText(RSingleton.getCheckin());
        txtCheckoutValue.setText(RSingleton.getCheckout());
        relativeLayout.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));

    }

}
