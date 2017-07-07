package com.av.dev.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 06/07/2017.
 */

public class ApiResponseBook {

    @SerializedName("code")
    private String code;
    @SerializedName("msg")
    private String message;

    public String getMessage() {
        return message;
    }

    public String getCode() {

        return code;
    }
}
