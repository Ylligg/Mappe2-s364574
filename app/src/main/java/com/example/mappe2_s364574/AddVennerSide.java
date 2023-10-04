package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
        RecyclerView recyclerView = findViewById(R.id.rcvenner);

        dataKilde = new DataKildeVenner(this);
        dataKilde.open();
        vennerliste = dataKilde.finnAlleVenner();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String navntxt = person.getText().toString();
                String tlftxt = tlf.getText().toString();

                if(navntxt.isEmpty()){
                    errornavn.setText("Skriv inn et navn");
                } else{
                    errornavn.setText("");
                }

                if(tlftxt.isEmpty()){
                    errortlf.setText("Skriv inn et nummer");
                } else{
                    errortlf.setText("");
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