package com.av.dev.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by CodeSyaona on 05/07/2017.
 */

public interface ApiServiceBooking {

    @POST("hotel/getHotels")
    @FormUrlEncoded
    Call<ApiResponseHotel> getHotel(@Field("location") String location);

    @POST("hotel/getHotelDetails")
    @FormUrlEncoded
    Call<ApiResponseHotelDetails> getHotelDetails(@Field("hotel_id") String id, @Field("check_in") String checkin,
                                                  @Field("check_out") String checkout);

    @POST("/book/addBooking")
    @FormUrlEncoded
    Call<ApiResponseBook> bookUser(@Field("user_id") String id, @Field("first_name") String firstName,
                                   @Field("last_name") String lastName, @Field("hotel_name") String hotelName,
                                   @Field("check_in") String checkin, @Field("check_out") String checkout);



}
