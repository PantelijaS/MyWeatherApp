package com.myweatherapp.units;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    public static final String API_ID="f2ede1f5f104f2b1a5518f30d7b475ca";

    public static String convertToDate (long dateParams){
        Date date = new Date(dateParams*1000l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm EEEE yyyy");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static String convertToTime (long timeParams){
        Date date = new Date(timeParams*1000l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static String convertToDateForecast (long dateParams){
        Date date = new Date(dateParams*1000l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm EEEE ");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static int convertToInt (double tempParams){
        DecimalFormat formatter = new DecimalFormat("#0");
        int  temp = Integer.parseInt(formatter.format(tempParams));
        return temp;
    }
}
