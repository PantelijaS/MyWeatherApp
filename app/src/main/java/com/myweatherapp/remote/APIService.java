package com.myweatherapp.remote;

import com.myweatherapp.model.Forcast;
import com.myweatherapp.model.WeatherModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("weather")
    Observable<WeatherModel> getWeatherModel(@Query("q") String q, @Query("appid") String appid,@Query("units") String units);

    @GET("forecast")
    Observable<Forcast> getForcastModel(@Query("q") String q, @Query("appid") String appid, @Query("units") String units);
}
