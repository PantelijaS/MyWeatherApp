package com.myweatherapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Common {

    public static final String API_ID="f2ede1f5f104f2b1a5518f30d7b475ca";

    public static String convertToDate (long dateParams){
        Date date = new Date(dateParams*1000l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm EEEE mm yyyy");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static String convertToTime (long timeParams){
        Date date = new Date(timeParams*1000l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
