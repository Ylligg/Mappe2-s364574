package com.example.mappe2_s364574;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MinBroadcastReceiver extends BroadcastReceiver {

    public MinBroadcastReceiver(){}

    // starter en broadcast -> Minperodisk (alarm) -> Minsendservice (notifikasjon + sms til venn)
    @Override
    public void onReceive(Context context, Intent intent) {
            Intent i = new Intent(context, MinPeriodisk.class);
            context.startService(i);
    }
}
