package com.example.laptophome.weatherapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptophome.weatherapp.Model.WeatherResult;
import com.google.android.gms.common.internal.service.Common;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Todayweather_fragment extends Fragment {
    ImageView weather;
    TextView city_name,date_time,humidity,tempreature,pressure,sunrise,sunset,wind,geo_coords,descriptions;
    ProgressBar progressBar;
    LinearLayout Root_layout;
    CompositeDisposable compositeDisposable;
    Retrofit_interface service;

    public Todayweather_fragment() {
       compositeDisposable=new CompositeDisposable();
        Retrofit retrofit=Retrofit_Client.getInstance();
        service=retrofit.create(Retrofit_interface.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vv= inflater.inflate(R.layout.fragment_todayweather_fragment, container, false);
        Root_layout=(LinearLayout)vv.findViewById(R.id.root_layout);
        weather=(ImageView)vv.findViewById(R.id.Weather_img);
        descriptions=(TextView)vv.findViewById(R.id.Description);
        date_time=(TextView)vv.findViewById(R.id.date_time);
        tempreature=(TextView)vv.findViewById(R.id.text_temperature);
        humidity=(TextView)vv.findViewById(R.id.text_humidity);
        pressure=(TextView)vv.findViewById(R.id.text_pressure);
        sunrise=(TextView)vv.findViewById(R.id.Sunrise);
        sunset=(TextView)vv.findViewById(R.id.text_sunset);
        wind=(TextView)vv.findViewById(R.id.text_wind);
        geo_coords=(TextView)vv.findViewById(R.id.text_geo);
        progressBar=(ProgressBar)vv.findViewById(R.id.progress_par);
        getweatherinformation();




      return vv;
    }

    public void getweatherinformation()
    {
       compositeDisposable.add(service.getwehaterbylat(String.valueOf(MyApiKey.current_location.getLatitude()),
               String.valueOf(MyApiKey.current_location.getLongitude()),MyApiKey.Key,"metric")
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<WeatherResult>() {
                              @Override
                              public void accept(WeatherResult weatherResult) throws Exception {

                                //load images
                                  Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                                  .append(weatherResult.getWeather().get(0).getIcon())
                                  .append(".png").toString()).into(weather);
                                  ////////////////////

                                 descriptions.setText(new StringBuilder("Weather in ").append(weatherResult.getName()).toString());
                                  tempreature.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp())).append("°C").toString());
                                  date_time.setText(MyApiKey.dataformat(weatherResult.getDt()));
                                  pressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append("hdp").toString());
                                  humidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append("%").toString());
                                  sunrise.setText(MyApiKey.converthour(weatherResult.getSys().getSunrise()));
                                  sunset.setText(MyApiKey.converthour(weatherResult.getSys().getSunset()));
                                 geo_coords.setText(new StringBuilder(" ").append(weatherResult.getCoord()).append(" ").toString());
                                 wind.setText(new StringBuilder("Spe").append(weatherResult.getWind().getSpeed()).append("Deg").append(weatherResult.getWind().getDeg()).toString());
                                  progressBar.setVisibility(View.GONE);
                                  Root_layout.setVisibility(View.VISIBLE);




                              }

                          }, new Consumer<Throwable>() {
                              @Override
                              public void accept(Throwable throwable) throws Exception {
                                  Toast.makeText(getActivity(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                              }
                          }


               )

       );


    }

}
