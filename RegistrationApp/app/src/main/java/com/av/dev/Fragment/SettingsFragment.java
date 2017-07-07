package com.av.dev.Fragment;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.av.dev.Core.AppController;
import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.REngine;
import com.av.dev.Core.RSharedPreferences;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public static SettingsFragment INSTANCE =null;
    public ProgressDialog progressDialog;

    @BindView(R.id.dialoglayout)
    LinearLayout linearLayout;
    @BindView(R.id.txttitle)
    TextView txtTitle;
    @BindView(R.id.txtlayout)
    TextView txtLayout;
    @BindView(R.id.btnred)
    ImageView btnRed;
    @BindView(R.id.btnblue)
    ImageView btnBlue;
    @BindView(R.id.btngreen)
    ImageView btnGreen;
    @BindView(R.id.btnlistview)
    Button btnListView;
    @BindView(R.id.btngridview)
    Button btnGridView;
    @BindView(R.id.btnsave)
    Button btnSave;
    @BindView(R.id.linearred)
    LinearLayout linearRed;
    @BindView(R.id.linearblue)
    LinearLayout linearBlue;
    @BindView(R.id.lineargreen)
    LinearLayout linearGreen;
    @BindView(R.id.linearbtnlist)
    LinearLayout linearList;
    @BindView(R.id.linearbtngrid)
    LinearLayout linearGrid;

    public String colorCheck = "";
    public String isListSelected = "";


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        INSTANCE = this;

        progressDialog = new ProgressDialog(getActivity());

        initDisplayViews();

        return view;
    }

    public void initDisplayViews(){
        linearLayout.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
        txtTitle.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        txtLayout.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        btnListView.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedColorBackGround)));
        btnGridView.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
        btnSave.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
        btnListView.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        btnGridView.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        btnSave.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        if (!colorCheck.equalsIgnoreCase("")){
            if (colorCheck.equalsIgnoreCase("red")){
                linearRed.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
                linearBlue.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
                linearGreen.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
            } else if (colorCheck.equalsIgnoreCase("blue")){
                linearBlue.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
                linearRed.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
                linearGreen.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
            } else if (colorCheck.equalsIgnoreCase("green")){
                linearGreen.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
                linearRed.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
                linearBlue.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
            }
        }

        if (!isListSelected.equalsIgnoreCase("")){
            if (isListSelected.equalsIgnoreCase("list")){
                btnListView.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
                btnGridView.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
            } else if (isListSelected.equalsIgnoreCase("grid")){
                btnListView.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));
                btnGridView.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
            }
        }

    }

    @OnClick(R.id.btnsave)
    public void onClickSave(){
        REngine.requestApiUpdateSettings((BaseActivity) getActivity(), progressDialog);
//        initDisplayViews();
    }

    @OnClick(R.id.btnlistview)
    public void onClickList(){
        RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayout, "listview");
        isListSelected = "list";
        initDisplayViews();
    }

    @OnClick(R.id.btngridview)
    public void onClickGrid(){
        RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayout, "gridview");
        isListSelected = "grid";
        initDisplayViews();
    }

    @OnClick(R.id.btnred)
    public void onClickRed(){
        REngine.requestApiSelectColor("red","home",progressDialog);
//        initDisplayViews();

        colorCheck = "red";

    }

    @OnClick(R.id.btnblue)
    public void onClickBlue(){
        REngine.requestApiSelectColor("blue", "home", progressDialog);
//        initDisplayViews();

        colorCheck = "blue";

    }

    @OnClick(R.id.btngreen)
    public void onClickGreen(){
        REngine.requestApiSelectColor("green", "home", progressDialog);
//        initDisplayViews();

        colorCheck = "green";

    }

}
