package com.av.dev.Object;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by CodeSyaona on 03/07/2017.
 */

@Parcel
public class DataObject {

    @SerializedName("background_color")
    private String backgroundColor;
    @SerializedName("text_color")
    private String textColor;
    @SerializedName("color_name")
    private String colorName;
    @SerializedName("_id")
    private String colorId;
    @SerializedName("layout_background")
    private String layoutBackground;

    public String getLayoutBackground() {
        return layoutBackground;
    }

    public void setLayoutBackground(String layoutBackground) {
        this.layoutBackground = layoutBackground;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBackgroundColor() {

        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


}
