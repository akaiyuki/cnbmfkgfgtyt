package com.av.dev.Api;

import com.av.dev.Object.HotelObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by CodeSyaona on 05/07/2017.
 */

public class ApiResponseHotel {

    @SerializedName("code")
    private String code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")

    private ArrayList<HotelObject> hotelObject;

    public String getMsg() {
        return msg;
    }

    public String getCode() {

        return code;
    }

    public ArrayList<HotelObject> getHotelObject() {

        return hotelObject;
    }

}
