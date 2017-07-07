package com.av.dev.Core;

import android.view.View;

import com.av.dev.Object.HotelObject;

import java.util.ArrayList;

/**
 * Created by CodeSyaona on 03/07/2017.
 */

public class RSingleton {

    public static String selectedColor;
    public static String selectedFragment;
    public static String selectedLayout;
    public static String location;
    public static ArrayList<HotelObject> resultHotels;
    public static String currentFragment;
    public static String checkin;
    public static String checkout;
    public static View currentView;
    public static String hotelId;
    public static String selectedHotel;

    public static String getSelectedHotel() {
        return selectedHotel;
    }

    public static void setSelectedHotel(String selectedHotel) {
        RSingleton.selectedHotel = selectedHotel;
    }

    public static String getHotelId() {
        return hotelId;
    }

    public static void setHotelId(String hotelId) {
        RSingleton.hotelId = hotelId;
    }

    public static View getCurrentView() {
        return currentView;
    }

    public static void setCurrentView(View currentView) {
        RSingleton.currentView = currentView;
    }

    public static String getCheckout() {
        return checkout;
    }

    public static void setCheckout(String checkout) {
        RSingleton.checkout = checkout;
    }

    public static String getCheckin() {

        return checkin;
    }

    public static void setCheckin(String checkin) {
        RSingleton.checkin = checkin;
    }

    public static String getCurrentFragment() {
        return currentFragment;
    }

    public static void setCurrentFragment(String currentFragment) {
        RSingleton.currentFragment = currentFragment;
    }

    public static ArrayList<HotelObject> getResultHotels() {
        return resultHotels;
    }

    public static void setResultHotels(ArrayList<HotelObject> resultHotels) {
        RSingleton.resultHotels = resultHotels;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        RSingleton.location = location;
    }

    public static String getSelectedLayout() {
        return selectedLayout;
    }

    public static void setSelectedLayout(String selectedLayout) {
        RSingleton.selectedLayout = selectedLayout;
    }

    public static String getSelectedFragment() {
        return selectedFragment;
    }

    public static void setSelectedFragment(String selectedFragment) {
        RSingleton.selectedFragment = selectedFragment;
    }

    public static String getSelectedColor() {
        return selectedColor;
    }

    public static void setSelectedColor(String selectedColor) {
        RSingleton.selectedColor = selectedColor;
    }

}
