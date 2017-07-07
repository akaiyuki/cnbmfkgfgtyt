package com.av.dev.Core;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.av.dev.Fragment.LoginFragment;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;

/**
 * Created by CodeSyaona on 03/07/2017.
 */

public class RDialog {

    public static void showChooseColorLogin(final BaseActivity baseActivity, final String fragment){

        final Dialog dialog = new Dialog(baseActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_color);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        LinearLayout linearLayout = dialog.findViewById(R.id.dialoglayout);
        linearLayout.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));

        TextView txtTitle = dialog.findViewById(R.id.txttitle);
        txtTitle.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        TextView txtLayout = dialog.findViewById(R.id.txtlayout);
        txtLayout.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        ImageView btnRed = dialog.findViewById(R.id.btnred);
        ImageView btnBlue = dialog.findViewById(R.id.btnblue);
        ImageView btnGreen = dialog.findViewById(R.id.btngreen);

        Button btnListView = dialog.findViewById(R.id.btnlistview);
        Button btnGridView = dialog.findViewById(R.id.btngridview);

        Button btnSave = dialog.findViewById(R.id.btnsave);

        btnListView.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.selectedColorBackGround)));
        btnGridView.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));
        btnSave.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedColorBackGround)));

        btnListView.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        btnGridView.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));
        btnSave.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                REngine.requestApiUpdateSettings(baseActivity);
            }
        });

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayout, "listview");

            }
        });

        btnGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayout, "gridview");
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                REngine.requestApiSelectColor("red",fragment);
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                REngine.requestApiSelectColor("blue", fragment);
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                REngine.requestApiSelectColor("green", fragment);
            }
        });


        dialog.show();

    }

    public static void showSuccessDialog(BaseActivity baseActivity, String message){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(baseActivity);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}
