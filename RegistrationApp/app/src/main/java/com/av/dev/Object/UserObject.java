package com.av.dev.Object;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by CodeSyaona on 04/07/2017.
 */

@Parcel
public class UserObject {

    @SerializedName("layout")
    private String layout;
    @SerializedName("layout_color")
    private String layoutColor;
    @SerializedName("text_color")
    private String textColor;
    @SerializedName("background_color")
    private String backgroundColor;
    @SerializedName("password")
    private String password;
    @SerializedName("username")
    private String username;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public String getBackgroundColor() {

        return backgroundColor;
    }

    public String getTextColor() {

        return textColor;
    }

    public String getLayoutColor() {

        return layoutColor;
    }

    public String getLayout() {

        return layout;
    }

}
