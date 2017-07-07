package com.av.dev.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by CodeSyaona on 04/07/2017.
 */

public interface ApiServiceUser {

    @POST("login")
    @FormUrlEncoded
    Call<ApiResponseUser> userLogin(@Field("username") String username, @Field("password") String password);

    @POST("user/updateSettings")
    @FormUrlEncoded
    Call<ApiResponseUser> updateSettings(@Field("user_id") String userId, @Field("text_color") String textColor,
                                         @Field("background_color") String backgroundColor, @Field("layout_color") String layoutColor,
                                         @Field("layout") String layout);

    @POST("user/add")
    @FormUrlEncoded
    Call<ApiResponseUser> signUpUser(@Field("first_name") String fName, @Field("last_name") String lName,
                                     @Field("username") String userName, @Field("password") String password,
                                     @Field("background_color") String background, @Field("text_color") String text,
                                     @Field("layout_color") String layoutColor, @Field("layout") String layout);

}
