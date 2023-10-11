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

        // åpner avtaler db for å lete etter om det er noen avtaler i dag
        dataKilde = new DataKildeAvtaler(this);
        dataKilde.open();
        avtaler = dataKilde.finnAlleAvtaler();

        // finner dagen i dag
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        String notiTittel = getResources().getString(R.string.notificationTittel);
        String notimsg1 = getResources().getString(R.string.notificationmsg1);
        String notimsg2 = getResources().getString(R.string.notificationmsg2);
        String notimsg3 = getResources().getString(R.string.notificationmsg3);
        // ser gjennom avtaler for å se om det er noen som har avtale i dag, hvis ja så skal en notifikasjon sendes til brukeren
        for(Avtale avtale : avtaler) {

            if (avtale.datoforMøte.equals(dtf.format(now))) {

                NotificationCompat.Builder notifikasjon = new NotificationCompat.Builder(this, "MinKanal")
                        .setContentTitle(notiTittel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(notimsg1 + avtale.navnpåPerson + notimsg2 + avtale.møtested + notimsg3 + avtale.klokkeslettforMøte))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE);


                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(notificationtall, notifikasjon.build());
                notificationtall++;

            }
        }


        return super.onStartCommand(intent, flags, startId);

    }



}
