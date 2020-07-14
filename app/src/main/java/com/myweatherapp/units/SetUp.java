package com.myweatherapp.units;

import android.content.Context;
import android.content.SharedPreferences;

public class SetUp {

    public static void saveDefaultCityName(Context kontekst, String name) {
        SharedPreferences podesavanja = kontekst.getSharedPreferences("setup", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = podesavanja.edit();
        editor.putString("cityName", name);
        editor.apply();
    }

    public static String returnDefaultCityName(Context kontekst) {
        SharedPreferences podesavanja = kontekst.getSharedPreferences("setup", Context.MODE_PRIVATE);
        return podesavanja.getString("cityName", null);
    }


    public static void saveCityName(Context kontekst, String name) {
        SharedPreferences podesavanja = kontekst.getSharedPreferences("setup", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = podesavanja.edit();
        editor.putString("cityName", name);
        editor.apply();
    }

    public static String returnCityName(Context kontekst) {
        SharedPreferences podesavanja = kontekst.getSharedPreferences("setup", Context.MODE_PRIVATE);
        return podesavanja.getString("cityName", null);
    }
}
