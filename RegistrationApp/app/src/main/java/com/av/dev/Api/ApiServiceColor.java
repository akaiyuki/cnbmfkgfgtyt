package com.av.dev.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by CodeSyaona on 03/07/2017.
 */

public interface ApiServiceColor {


    @POST("changeColor")
    @FormUrlEncoded
    Call<ApiResponse> selectedColor(@Field("color_name") String selectedColor);


}
