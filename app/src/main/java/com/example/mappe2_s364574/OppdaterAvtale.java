package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class OppdaterAvtale extends AppCompatActivity {

    public DataKildeAvtaler dataKildeavtaler;
    public DataKildeVenner dataKildevenner;
    private List<Avtale> avtaleliste;
    private List<Venner> vennerliste;
    long id =0;
    String valgtNavn ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oppdater_avtale);

        Spinner spinner = findViewById(R.id.spinnerOppdater);
        EditText dato = findViewById(R.id.editDatoOppdater);
        EditText klokken = findViewById(R.id.editTidOppdater);
        EditText sted = findViewById(R.id.editStedOppdater);
        ImageButton oppdaterknapp = findViewById(R.id.lagAvtaleknappOppdater);

        // åpner både venner og avtale databasen for å hente venner listen og at man kan oppdatere avtalen

        dataKildeavtaler = new DataKildeAvtaler(this);
        dataKildeavtaler.open();

        dataKildevenner = new DataKildeVenner(this);
        dataKildevenner.open();

        avtaleliste = dataKildeavtaler.finnAlleAvtaler();
        vennerliste = dataKildevenner.finnAlleVenner();

        // henter id av personen som skal oppdateres
        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");

        // lager en dropdownmeny
        ArrayAdapter<Venner> adapter = new ArrayAdapter<Venner>(this, android.R.layout.simple_spinner_dropdown_item,vennerliste);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //når vi velger et element i dropdown menyen så vil navnet bli valgt og skal oppdateres
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valgtNavn = vennerliste.get(i).getNavn();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // setter verdiene i edittext for å se hva som skal endres
        for(Avtale avtale : avtaleliste){
            if (avtale.getId() == id){
                dato.setText(avtale.datoforMøte);
                klokken.setText(avtale.klokkeslettforMøte);
                sted.setText(avtale.møtested);
            }
        }

        // trykker for å oppdatere avtale og updateavtale databasen blir brukt og fullført
        oppdaterknapp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                dataKildeavtaler.updateAvtale(id, valgtNavn, dato.getText().toString(), klokken.getText().toString() , sted.getText().toString());
                Intent intent = new Intent(OppdaterAvtale.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}