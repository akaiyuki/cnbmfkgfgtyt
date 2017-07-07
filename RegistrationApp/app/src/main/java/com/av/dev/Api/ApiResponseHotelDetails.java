package com.av.dev.Api;

import com.av.dev.Object.HotelDetailObject;
import com.av.dev.Object.HotelObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 06/07/2017.
 */

public class ApiResponseHotelDetails {

    @SerializedName("code")
    private String code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private HotelDetailObject hotelDetailObject;

    public HotelDetailObject getHotelDetailObject() {
        return hotelDetailObject;
    }

    public String getMsg() {

        return msg;
    }

    public String getCode() {

        return code;
    }
}
