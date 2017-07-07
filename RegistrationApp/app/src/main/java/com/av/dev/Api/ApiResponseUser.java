package com.av.dev.Api;

import com.av.dev.Object.UserObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 04/07/2017.
 */

public class ApiResponseUser {

    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private UserObject data;
    @SerializedName("code")
    private int status;

    public UserObject getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
