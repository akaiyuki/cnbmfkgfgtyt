package com.av.dev.Object;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by CodeSyaona on 05/07/2017.
 */
@Parcel
public class HotelObject {
    @SerializedName("_id")
    private String hotelId;
    @SerializedName("hotel_name")
    private String hotelName;
    @SerializedName("address")
    private String address;
    @SerializedName("location")
    private String location;
    @SerializedName("contact")
    private String contact;
    @SerializedName("star")
    private String star;
    @SerializedName("description")
    private String description;
    @SerializedName("amenities")
    private String amenities;
    @SerializedName("img_banner")
    private String imgBanner;
    @SerializedName("img_profile")
    private String imgProfile;
    @SerializedName("status")
    private String status;

    public String getHotelId() {
        return hotelId;
    }

    public String getStatus() {
        return status;
    }

    public String getImgProfile() {

        return imgProfile;
    }

    public String getImgBanner() {

        return imgBanner;
    }

    public String getAmenities() {

        return amenities;
    }

    public String getDescription() {

        return description;
    }

    public String getStar() {

        return star;
    }

    public String getContact() {

        return contact;
    }

    public String getLocation() {

        return location;
    }

    public String getAddress() {

        return address;
    }

    public String getHotelName() {

        return hotelName;
    }
}
