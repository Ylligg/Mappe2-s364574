package com.example.mappe2_s364574;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VennerSide extends AppCompatActivity {
    public DataKildeVenner dataKilde;
    private List<Venner> vennerliste;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venner_side);

        // henter listen og knappen for å legge til ekstra venner
        RecyclerView recyclerView = findViewById(R.id.rcvenner);
        Button add = findViewById(R.id.add);

        // åpner databasen for venner tabellen og legger verdiene i vennerarrayen
        dataKilde = new DataKildeVenner(this);
        dataKilde.open();
        vennerliste = dataKilde.finnAlleVenner();

        // sorterer venner i alfabetisk rekkefølge
        Collections.sort(vennerliste, new Comparator<Venner>() {
            @Override
            public int compare(Venner o1, Venner o2) {
                return o1.navn.compareToIgnoreCase(o2.navn);
            }
        });

        // legger inn den sorterte arrayen i recyclerViewet slik de blir vist på den måten at de har dataene vist
        // og knapper for å oppdatere og slette vennen
        recyclerView.setLayoutManager(new LinearLayoutManager(VennerSide.this));
        Venner_adapter adapter = new Venner_adapter(VennerSide.this, vennerliste);
        recyclerView.setAdapter(adapter);

        // add knapp for å gå til side for å legge inn nye venner
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddVenner();
            }
        });

    }

    // db er åpen
    @Override
    protected void onResume() {
        dataKilde.open();
        super.onResume();
    }

    // db stenges for CRUD
    @Override
    protected void onPause() {
        dataKilde.close();
        super.onPause();
    }

    // går til ny side
    public void openAddVenner() {
        Intent intent = new Intent(VennerSide.this, AddVennerSide.class);
        startActivity(intent);
    }

    // når man trykker bak så er man tilbake til hoved siden
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VennerSide.this, MainActivity.class);
        startActivity(intent);
    }
}