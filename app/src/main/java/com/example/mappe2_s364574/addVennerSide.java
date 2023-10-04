package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;


public class addVennerSide extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_venner_side);

        EditText person = findViewById(R.id.editnavn);
        EditText tlf = findViewById(R.id.edittlf);
        ImageButton add = findViewById(R.id.addpersonknapp);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String navntxt = person.getText().toString();
                String tlftxt = tlf.getText().toString();

                Intent intent = new Intent(addVennerSide.this, VennerSide.class);
                intent.putExtra("keynavn", navntxt);
                intent.putExtra("keytlf", tlftxt);
                startActivity(intent);

            }
        });
    }
}