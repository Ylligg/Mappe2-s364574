package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {


    ArrayList<Avtale> møter = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rc);
        Button vennerButton = findViewById(R.id.vennerid);
        Button avtalerButton = findViewById(R.id.avtalerid);
        Button preferanseerButton = findViewById(R.id.preferanserid);

        avtalerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAvtaler();
            }
        });

        vennerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVenner();
            }
        });

        preferanseerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPref();
            }
        });

        setupMøter();

        DateFormat dato = new SimpleDateFormat("dd.MM.yyyy");
        Collections.sort(møter, new Comparator<Avtale>() {
            @Override
            public int compare(Avtale o1, Avtale o2) {

                try {
                     int datoSort = dato.parse(o1.datoforMøte).compareTo(dato.parse(o2.datoforMøte));
                    return datoSort;
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });


        Møte_adapter adapter = new Møte_adapter(this, møter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setupMøter(){
        String[] navnVenner = getResources().getStringArray(R.array.navnVenner);
        String[] datoMøte = getResources().getStringArray(R.array.datomøte);
        String[] klokke = getResources().getStringArray(R.array.klokkeslettforMøte);
        String[] sted = getResources().getStringArray(R.array.stedforMøte);

        for(int i=0; i < navnVenner.length; i++){
            møter.add(new Avtale(
                    navnVenner[i],
                    datoMøte[i],
                    klokke[i],
                    sted[i]));
        }
    }

    public void openVenner() {
        // bytter aktivitet til Sprok
        Intent intent = new Intent(this, VennerSide.class);
        startActivity(intent);
    }
    public void openAvtaler() {
        // bytter aktivitet til Sprok
        Intent intent = new Intent(this, LagAvtaleSide.class);
        startActivity(intent);
    }
    public void openPref() {
        // bytter aktivitet til Sprok
        Intent intent = new Intent(this, PrefSide.class);
        startActivity(intent);
    }
}