package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class OppdaterVenner extends AppCompatActivity {
    public DataKildeVenner dataKilde;
    private List<Venner> vennerliste;
    long id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oppdater_venner);

        TextView navn = findViewById(R.id.editnavnoppdater);
        TextView tlf = findViewById(R.id.edittlfoppdater);
        ImageButton oppdaterknapp = findViewById(R.id.oppdaterpersonknapp);

        dataKilde = new DataKildeVenner(this);
        dataKilde.open();
        vennerliste = dataKilde.finnAlleVenner();

        // henter id som ble sendt fra venner adapter
        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");

        // går gjennom vennerlisten og finner personen med samme id som vi skulle redigere, når vi har funnet den personen så legger vi navn og tlf i edittext for å kunne bli lettere endret
        for(Venner venn : vennerliste){
            if (venn.getId() == id){
                navn.setText(venn.navn);
                tlf.setText(venn.telefonnummer);
            }
        }

        // når brukern har endret, så sendes den til updatevenn med alle parameterne og så blir brukeren sendt tilbake til venner siden
        oppdaterknapp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                dataKilde.updateVenn(id, navn.getText().toString(), tlf.getText().toString());
                Intent intent = new Intent(OppdaterVenner.this, VennerSide.class);
                startActivity(intent);
            }
        });
    }
}