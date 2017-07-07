package com.av.dev.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.av.dev.Api.ApiResponseUser;
import com.av.dev.Api.RestClient;
import com.av.dev.Core.BaseActivity;
import com.av.dev.Core.RDialog;
import com.av.dev.Core.REngine;
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
public class SignUpFragment extends Fragment {

    private ProgressDialog progressDialog;

    @BindView(R.id.editfname)
    EditText editFname;
    @BindView(R.id.editlname)
    EditText editLname;
    @BindView(R.id.editusername)
    EditText editUsername;
    @BindView(R.id.editpassword)
    EditText editPassword;
    @BindView(R.id.btnsign)
    Button btnSignup;


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(getActivity());

        return view;
    }

    @OnClick(R.id.btnsign)
    public void onClick(){

        if (editFname.getText().length() != 0 && editLname.getText().length() != 0
                && editUsername.getText().length() != 0 && editPassword.getText().length() != 0){

            String backgroundColor = "#717371";
            String textColor = "#FFFFFF";
            String layoutColor = "#2f302f";
            String layout = "listview";

            requestApiSignUpUser(editFname.getText().toString(),editLname.getText().toString(),editUsername.getText().toString(),
                    editPassword.getText().toString(),backgroundColor,textColor,layoutColor,layout);

        } else {
            RDialog.showSuccessDialog((BaseActivity) getActivity(), "Please input necessary details");
        }

    }


    private void requestApiSignUpUser(String fname, String lname, String username,
                                      String password, String background, String text,
                                      String lcolor, String layout){

        progressDialog.show();

        RestClient restClient = new RestClient(RestClient.userApiService);
        Call<ApiResponseUser> call = restClient.getApiServiceUser().signUpUser(fname,lname,username,password,background,text,lcolor,layout);
        call.enqueue(new Callback<ApiResponseUser>() {
            @Override
            public void onResponse(Call<ApiResponseUser> call, Response<ApiResponseUser> response) {

                progressDialog.dismiss();

                if (response.isSuccessful()){
                    RDialog.showSuccessDialog((BaseActivity) getActivity(), response.body().getMessage());
                    REngine.switchFragment((BaseActivity) getActivity(), new WelcomeFragment(), ((BaseActivity)getActivity()).getFrameLayout());

                }

            }

            @Override
            public void onFailure(Call<ApiResponseUser> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

}
