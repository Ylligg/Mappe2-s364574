package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class LagAvtaleSide extends AppCompatActivity {
    private List<Venner> vennerliste;
    public DataKildeVenner dataKilde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lag_avtale_side);

        dataKilde = new DataKildeVenner(this);
        dataKilde.open();
        vennerliste = dataKilde.finnAlleVenner();

        Spinner spinner = findViewById(R.id.spinner);


        ArrayAdapter<Venner> adapter = new ArrayAdapter<Venner>(this, android.R.layout.simple_spinner_dropdown_item,vennerliste);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}