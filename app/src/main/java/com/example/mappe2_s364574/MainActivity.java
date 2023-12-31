package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Avtale> avtaler = new ArrayList<>();
    public DataKildeAvtaler dataKilde;
    int datoSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rc);
        Button vennerButton = findViewById(R.id.vennerid);
        Button avtalerButton = findViewById(R.id.avtalerid);
        Button preferanseerButton = findViewById(R.id.preferanserid);

        // starter opp broadcastreceiver
        BroadcastReceiver myBroadcastReceiver = new MinBroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.example.service.MITTSIGNAL");
        filter.addAction("com.example.service.MITTSIGNAL");
        this.registerReceiver(myBroadcastReceiver, filter);

        // åpner databasen for avtaler for å vise de fram
        dataKilde = new DataKildeAvtaler(this);
        dataKilde.open();
        avtaler = dataKilde.finnAlleAvtaler();

        // sorterer avtalene utifra dato slik at den nærmeste datoen vises øverst til den med lengst dato fra i dag
        DateFormat dato = new SimpleDateFormat("dd.MM.yyyy");

        Collections.sort(avtaler, new Comparator<Avtale>() {
            @Override
            public int compare(Avtale o1, Avtale o2) {

                try {
                      datoSort= dato.parse(o1.datoforMøte).compareTo(dato.parse(o2.datoforMøte));
                } catch (ParseException e) {
                    System.out.println("feil");
                }
                return datoSort;
            }
        });

        // setter opp listen med den sorterte arrayen av avtaler
        Avtaler_adapter adapter = new Avtaler_adapter(this, avtaler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // lager en kanal for notifications og starter broadcasten for å vise de fram
        createNotificationChannel();
        sendBroadcast();


        avtalerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAvtaler();
            }
        });

        vennerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVenner();
            }
        });

        preferanseerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPref();
            }
        });

    }

    public void sendBroadcast(){
        Intent intent = new Intent();
        intent.setAction("com.example.service.MITTSIGNAL");
        sendBroadcast(intent);
    }

    private void createNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new
                NotificationChannel("MinKanal", "Min Kanal", importance);
        channel.setDescription("descripUon");
        NotificationManager notificationManager =
                getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    public void openVenner() {
        // bytter aktivitet til vennerside
        Intent intent = new Intent(this, VennerSide.class);
        startActivity(intent);
    }
    public void openAvtaler() {
        // bytter aktivitet til lagavtale side
        Intent intent = new Intent(this, LagAvtaleSide.class);
        startActivity(intent);
    }
    public void openPref() {
        // bytter aktivitet til preferanser
        Intent intent = new Intent(this, PrefSide.class);
        startActivity(intent);

    }
}