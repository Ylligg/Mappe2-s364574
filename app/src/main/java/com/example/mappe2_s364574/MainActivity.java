package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

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
        Intent intent = new Intent(this, AvtaleSide.class);
        startActivity(intent);
    }
    public void openPref() {
        // bytter aktivitet til Sprok
        Intent intent = new Intent(this, PrefSide.class);
        startActivity(intent);
    }
}