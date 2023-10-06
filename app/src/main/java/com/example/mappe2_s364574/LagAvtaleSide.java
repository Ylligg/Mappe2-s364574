package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.List;

public class LagAvtaleSide extends AppCompatActivity {
    private List<Venner> vennerliste;
    public DataKildeVenner dataKildevenner;
    public DataKildeAvtaler dataKildeavtaler;
    public String valgtNavn ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lag_avtale_side);


        dataKildevenner = new DataKildeVenner(this);
        dataKildevenner.open();
        vennerliste = dataKildevenner.finnAlleVenner();

        EditText dato = findViewById(R.id.editDato);
        EditText klokke = findViewById(R.id.editTid);
        EditText sted = findViewById(R.id.editSted);
        Spinner spinner = findViewById(R.id.spinner);


        ArrayAdapter<Venner> adapter = new ArrayAdapter<Venner>(this, android.R.layout.simple_spinner_dropdown_item,vennerliste);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        ImageButton add = findViewById(R.id.lagAvtaleknapp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              valgtNavn = vennerliste.get(i).getNavn();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dataKildeavtaler = new DataKildeAvtaler(this);
        dataKildeavtaler.open();




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String navntxt = valgtNavn;
                String datotxt = dato.getText().toString();
                String klokketxt = klokke.getText().toString();
                String stedtxt = sted.getText().toString();

                //hvis alt felt er fylt ut så lagrer vi avtalen
                if (!navntxt.isEmpty() && !datotxt.isEmpty() && !klokketxt.isEmpty() && !stedtxt.isEmpty()) {
                    Avtale avtale = dataKildeavtaler.leggInnAvtale(navntxt, datotxt, klokketxt, stedtxt);
                    Intent intent = new Intent(LagAvtaleSide.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}