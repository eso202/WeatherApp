package com.example.laptophome.weatherapp;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyApiKey {
    public static final String Key ="076cbb6fa3c1771dfd4c8ecea4595182";
    public static Location current_location=null;

    public static String dataformat(long dt) {

        Date date=new Date(dt*1000L);
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:MM  EEE dd/MM/YYYY");
        String formatted=dateFormat.format(date);
        return formatted;
    }

    public static String converthour(int dt) {

        Date date=new Date(dt*1000L);
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:MM ");
        String formatted=dateFormat.format(date);
        return formatted;

    }
}
