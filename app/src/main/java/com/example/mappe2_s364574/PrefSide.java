package com.example.mappe2_s364574;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrefSide extends AppCompatActivity {

    public static final String SharedPref = "sharedpref";
    EditText klokke, sms;
    ImageButton aktiver;

    public DataKildeVenner dataKildeVenner;
    public DataKildeAvtaler dataKildeAvtaler;

    private List<Avtale> avtaler;

    Switch aSwitch;

    final int SMS_Permission = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref_side);

        aSwitch = findViewById(R.id.prefswitch);
        klokke = findViewById(R.id.prefeditklokke);
        sms = findViewById(R.id.prefeditsms);
        aktiver = findViewById(R.id.prefsmsknapp);

        dataKildeVenner = new DataKildeVenner(this);
        dataKildeVenner.open();

        dataKildeAvtaler = new DataKildeAvtaler(this);
        dataKildeAvtaler.open();
        avtaler = dataKildeAvtaler.finnAlleAvtaler();






        aktiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lagrepref();
                lagsms();

            }
        });

        visData();

        if(checkpermisson(Manifest.permission.SEND_SMS)){
            System.out.println("Har permission nå");
        } else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_Permission);
        }



    }

    public void lagsms(){


        DateTimeFormatter tid = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime no = LocalDateTime.now();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();

        for(Avtale avtale : avtaler) {

            if (avtale.datoforMøte.equals(dtf.format(now))) {

                System.out.println("samme dag");


                if (tid.format(no).equals(klokke.getText().toString())) {

                    System.out.println("sende sms");

                    List<Venner> venner = dataKildeVenner.finnAlleVenner();;

                    for(Venner venn : venner){
                        if(avtale.navnpåPerson.equals(venn.navn)){
                            System.out.println("fant personen");
                            sendSMS(venn.telefonnummer);
                        }
                    }
                }

            }
        }
    }

    public void lagrepref() {
        // lager editor for å lagre variabler
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //editor.putInt(TALL1, bakgrunn5);
        editor.putString("klokke", klokke.getText().toString());
        editor.putString("sms", sms.getText().toString());
        editor.putInt("switch", 1);

        editor.apply();
        String dataSaved = getResources().getString(R.string.PrefLAGRET);
        Toast.makeText(this, dataSaved, Toast.LENGTH_SHORT).show();

    }

    public void visData() {
        // henter dataene som ble lagret og setter dem inn i varabler
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        klokke.setText(preferences.getString("klokke", ""));
        sms.setText(preferences.getString("sms", ""));
        aSwitch.isActivated();
    }

    public void sendSMS(String telefonnr){
        if(checkpermisson(Manifest.permission.SEND_SMS)){
            SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
            String melding = preferences.getString("sms", "Husk møtet ditt for i dag!");
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+47"+telefonnr, null, melding, null, null);
            System.out.println("smsen ble sendt");
            Toast.makeText(this, "Meldingen er sendt", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("ikke lov");
        }
    }

    public boolean checkpermisson(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);

    }
}