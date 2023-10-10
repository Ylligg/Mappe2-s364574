package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrefSide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref_side);


        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime nå = LocalDateTime.now();

        //når klokken er 6 (defualt) så vil en notifikasjon sendes til brukeren om han har noen avtaler som han har i dag
        if (time.format(nå).equals("06:00")) {
            // send sms
        }

    }
}