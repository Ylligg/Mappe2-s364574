package com.example.mappe2_s364574;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VennerSide extends AppCompatActivity {
    public ArrayList<Venner> venner = new ArrayList<>();
    int i =0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venner_side);

        RecyclerView recyclerView = findViewById(R.id.rcvenner);
        Button add = findViewById(R.id.add);

        setupVenner();

        // sorterer venner i alfabetisk rekkefølge
        Collections.sort(venner, new Comparator<Venner>() {
            @Override
            public int compare(Venner o1, Venner o2) {
                    return o1.navn.compareToIgnoreCase(o2.navn);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(VennerSide.this));
        Venner_adapter adapter = new Venner_adapter(VennerSide.this, venner);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddVenner();
                venner.add(new Venner("navn", "tlf"+i));
                i+=1;
                adapter.notifyItemInserted(venner.size() -1);
            }
        });


    }
    public void openAddVenner() {
        Intent intent = new Intent(VennerSide.this, addVennerSide.class);
        startActivity(intent);
    }

    private void setupVenner(){
        String[] navnVenner = getResources().getStringArray(R.array.navnVenner);
        String[] telfonnr = getResources().getStringArray(R.array.vennerTlfnr);

        for(int i=0; i < navnVenner.length; i++){
            venner.add(new Venner(
                    navnVenner[i],
                    telfonnr[i]));
        }
    }

}