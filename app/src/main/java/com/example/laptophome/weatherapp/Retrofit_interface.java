package com.example.laptophome.weatherapp;

import com.example.laptophome.weatherapp.Model.WeatherResult;
import com.example.laptophome.weatherapp.Model.Weatherforecastresult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Retrofit_interface {
    @GET("weather")
    Observable<WeatherResult> getwehaterbylat(@Query("lat") String lat,
                                              @Query("lon") String lon,
                                              @Query("appid") String appid,
                                              @Query("units") String unit);

    @GET("forecast")
    Observable<Weatherforecastresult> getwehaterforecast(@Query("lat") String lat,
                                                @Query("lon") String lon,
                                                @Query("appid") String appid,
                                                @Query("units") String unit);



}
