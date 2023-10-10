package com.example.mappe2_s364574;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MinSendService extends Service {
    List<Avtale> avtaler = new ArrayList<>();
    public DataKildeAvtaler dataKilde;
    int notificationtall=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Minservice", "Service laget");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "I MinService", Toast.LENGTH_SHORT).show();

        dataKilde = new DataKildeAvtaler(this);
        dataKilde.open();
        avtaler = dataKilde.finnAlleAvtaler();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();

        for(Avtale avtale : avtaler) {

            if (avtale.datoforMøte.equals(dtf.format(now))) {

                NotificationCompat.Builder notifikasjon = new NotificationCompat.Builder(this, "MinKanal")
                        .setContentTitle("Du har en avtale i dag!")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Du har en avtale med " + avtale.navnpåPerson + " og dere skal møtes ved " + avtale.møtested + " klokken " + avtale.klokkeslettforMøte))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE);


                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(notificationtall, notifikasjon.build());
                notificationtall++;

            }
        }

        //her blir det sendt sms også
        return super.onStartCommand(intent, flags, startId);

    }



}