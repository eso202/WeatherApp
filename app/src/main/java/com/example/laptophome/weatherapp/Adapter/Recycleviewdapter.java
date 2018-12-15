package com.example.laptophome.weatherapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptophome.weatherapp.Model.Weatherforecastresult;
import com.example.laptophome.weatherapp.MyApiKey;
import com.example.laptophome.weatherapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Recycleviewdapter extends RecyclerView.Adapter<Recycleviewdapter.myviewholder> {

Context context;
Weatherforecastresult weatherforecastresult;

    public Recycleviewdapter(Context context, Weatherforecastresult weatherforecastresult) {
        this.context = context;
        this.weatherforecastresult = weatherforecastresult;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vv=LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false);
        return new  myviewholder(vv);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {

       Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
              .append(weatherforecastresult.list.get(i).weather.get(0).getIcon())
                .append(".png").toString()).into(myviewholder.img);


       myviewholder.txt_day.setText(new StringBuilder(MyApiKey.dataformat(weatherforecastresult.list.get(i).dt)));
       myviewholder.txt_description.setText(new StringBuilder(weatherforecastresult.list.get(i).weather.get(0).getDescription()));
       myviewholder.text_temperature.setText(new StringBuilder(String.valueOf(weatherforecastresult.list.get(i).main.getTemp())).append("°C "));


    }

    @Override
    public int getItemCount() {
        return weatherforecastresult.list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {TextView txt_day,text_temperature,txt_description;
    ImageView img;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
           txt_day=(TextView)itemView.findViewById(R.id.forcast_date);
           text_temperature=(TextView)itemView.findViewById(R.id.forcast_temperature);
           txt_description=(TextView)itemView.findViewById(R.id.forecast_descript);
          img=(ImageView)itemView.findViewById(R.id.forcast_img);

        }
    }
}
