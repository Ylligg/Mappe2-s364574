package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public class AddVennerSide extends AppCompatActivity {

    private DataKildeVenner dataKilde;
    private ArrayAdapter<Venner> vennerArrayAdapter;
    private EditText oppgaveEditText;
    private List<Venner> vennerliste;

    private Venner_adapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_venner_side);

        EditText person = findViewById(R.id.editnavn);
        EditText tlf = findViewById(R.id.edittlf);
        TextView errornavn = findViewById(R.id.errornavn);
        TextView errortlf = findViewById(R.id.errortlf);
        ImageButton add = findViewById(R.id.addpersonknapp);

        // åpner databasen for venner tabellen og legger verdiene i vennerarrayen
        dataKilde = new DataKildeVenner(this);
        dataKilde.open();
        vennerliste = dataKilde.finnAlleVenner();

        // input validerer om det er skrevet noe inn, hvis ja så legger vi inn personen i databasen venner
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String navnerror = getResources().getString(R.string.addvennererrornavn);
                String tlferror = getResources().getString(R.string.addvennererrortlf);
                String navnerrortom = getResources().getString(R.string.addvennererrornavntom);
                String tlferrortom = getResources().getString(R.string.addvennererrortlftom);

                String navntxt = person.getText().toString();
                String tlftxt = tlf.getText().toString();

                if(navntxt.isEmpty()){
                    errornavn.setText(navnerror);
                } else{
                    errornavn.setText(navnerrortom);
                }

                if(tlftxt.isEmpty()){
                    errortlf.setText(tlferror);
                } else{
                    errortlf.setText(tlferrortom);
                }

                if (!navntxt.isEmpty() && !tlftxt.isEmpty()) {
                    Venner venn = dataKilde.leggInnVenn(navntxt, tlftxt);
                    Intent intent = new Intent(AddVennerSide.this, VennerSide.class);
                    startActivity(intent);
                }

            }
        });
    }
}