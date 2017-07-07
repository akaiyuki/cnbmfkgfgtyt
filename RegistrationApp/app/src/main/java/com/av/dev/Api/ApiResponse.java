package com.av.dev.Api;

import com.av.dev.Object.DataObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 03/07/2017.
 */

public class ApiResponse {

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    private DataObject data;

    @SerializedName("code")
    private int status;


    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }


    public DataObject getData() {
        return data;
    }

}
