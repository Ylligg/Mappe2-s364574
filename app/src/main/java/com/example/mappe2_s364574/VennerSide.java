package com.example.mappe2_s364574;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VennerSide extends AppCompatActivity {
    ArrayList<Venner> venner = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venner_side);

        RecyclerView recyclerView = findViewById(R.id.rcvenner);



        setupVenner();

        // sorterer venner i alfabetisk rekkefølge
        Collections.sort(venner, new Comparator<Venner>() {
            @Override
            public int compare(Venner o1, Venner o2) {
                    return o1.navn.compareToIgnoreCase(o2.navn);
            }
        });

        Venner_adapter adapter = new Venner_adapter(VennerSide.this, venner);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(VennerSide.this));

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