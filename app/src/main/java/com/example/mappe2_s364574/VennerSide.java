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
    public ArrayList<Venner> venner = new ArrayList<>();
    int i = 0;

    public DataKildeVenner dataKilde;
    private ArrayAdapter<Venner> vennerArrayAdapter;
    private List<Venner> vennerliste;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venner_side);


        RecyclerView recyclerView = findViewById(R.id.rcvenner);
        Button add = findViewById(R.id.add);


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


        recyclerView.setLayoutManager(new LinearLayoutManager(VennerSide.this));
        Venner_adapter adapter = new Venner_adapter(VennerSide.this, vennerliste);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddVenner();
            }
        });

    }
    @Override
    protected void onResume() {
        dataKilde.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataKilde.close();
        super.onPause();
    }


    public void openAddVenner() {
        Intent intent = new Intent(VennerSide.this, AddVennerSide.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VennerSide.this, MainActivity.class);
        startActivity(intent);
    }


}