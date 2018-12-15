package com.example.laptophome.weatherapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptophome.weatherapp.Adapter.Recycleviewdapter;
import com.example.laptophome.weatherapp.Model.WeatherResult;
import com.example.laptophome.weatherapp.Model.Weatherforecastresult;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
CompositeDisposable compositeDisposable;
    TextView forcast_city_name;
    Retrofit_interface service;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayout linearLayout;
static  BlankFragment instance;
public static BlankFragment getInstance()
{
    if(instance ==null)
        instance=new BlankFragment();
    return instance;
}

    public BlankFragment() {
        compositeDisposable=new CompositeDisposable();
        Retrofit retrofit=Retrofit_Client.getInstance();
        service=retrofit.create(Retrofit_interface.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_blank, container, false);
        forcast_city_name=(TextView)view.findViewById(R.id.text_cityname);
        linearLayout=(LinearLayout)view.findViewById(R.id.main_layout);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycle_view);
        progressBar=(ProgressBar)view.findViewById(R.id.progress_par);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        getforecastweather();

                return view;
    }

    public void getforecastweather()
    {

        compositeDisposable.add(service.getwehaterforecast(String.valueOf(MyApiKey.current_location.getLatitude()),
                String.valueOf(MyApiKey.current_location.getLongitude()),MyApiKey.Key,"metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Weatherforecastresult>() {
                               @Override
                               public void accept(Weatherforecastresult weatherforecastresult) throws Exception {
                                    DisplaYresultforecast(weatherforecastresult);
                                    progressBar.setVisibility(View.GONE);
                                    linearLayout.setVisibility(View.VISIBLE);
                               }

                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_LONG).show();

                               }
                           })

                );


    }

    public void DisplaYresultforecast(Weatherforecastresult weatherforecastresult)
    {
      forcast_city_name.setText(new StringBuilder(weatherforecastresult.city.name.toString()));
       Recycleviewdapter adapter=new Recycleviewdapter(getContext(),weatherforecastresult);
        recyclerView.setAdapter(adapter);
    }



}
