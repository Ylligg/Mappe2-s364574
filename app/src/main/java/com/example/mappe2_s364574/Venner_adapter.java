package com.example.mappe2_s364574;

import static android.content.Intent.getIntent;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mappe2_s364574.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Venner_adapter extends RecyclerView.Adapter<Venner_adapter.MyViewHolder>{
    Context context;
    public static DataKildeVenner dataKilde;
    List<Venner> venner;

    public Venner_adapter(Context context, List<Venner> venner){
        this.context = context;
        this.venner = venner;
    }

    // tar i bruk recycler_view_row_venner for å lage radene for listen i venner siden
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_venner, parent, false);

        return new MyViewHolder(view).linkAdpater(this);
    }

    // gir radene navn og tlf og henter info fra venner listen
    @Override
    public void onBindViewHolder(@NonNull Venner_adapter.MyViewHolder holder, int position) {
        holder.navn.setText(venner.get(position).navn);
        holder.tlf.setText(venner.get(position).telefonnummer);
    }

    @Override
    public int getItemCount() {
        return venner.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView navn,tlf;
        private Venner_adapter adapter;
        long vennid=0;
        String slettmsg, slettJA, slettNei,oppdatermsg, oppdaterJA, oppdaterNei;

        Context context;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // henter textviewene i siden
            navn = itemView.findViewById(R.id.navn);
            tlf = itemView.findViewById(R.id.tlfnr);

            slettmsg = itemView.getResources().getString(R.string.slettVennSafeguard);
            slettJA = itemView.getResources().getString(R.string.slettVennJa);
            slettNei = itemView.getResources().getString(R.string.slettVennNei);

            // henter slett knappen og lager en onclicklistener som lager en alert/safeguard
            itemView.findViewById(R.id.slett).setOnClickListener(View -> {

                new AlertDialog.Builder(adapter.context)
                        .setMessage(slettmsg)
                        .setCancelable(false)
                        .setPositiveButton(slettJA, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // henter vennid og bruker id for å slette personen
                                vennid = adapter.venner.get(getAdapterPosition()).getId();
                                dataKilde = new DataKildeVenner(adapter.context);
                                dataKilde.open();
                                dataKilde.slettVenn(vennid);
                                // fjerner personen fra adapteren slik at det vises også på skjermen at personen er slettet
                                adapter.venner.remove(getAdapterPosition());
                                adapter.notifyItemRemoved(getAdapterPosition());
                            }
                        })
                        .setNegativeButton(slettNei, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

            });

            // henter fra strings
            oppdatermsg = itemView.getResources().getString(R.string.oppdaterVennSafeguard);
            oppdaterJA = itemView.getResources().getString(R.string.oppdaterVennJa);
            oppdaterNei = itemView.getResources().getString(R.string.oppdaterVennNei);

            itemView.findViewById(R.id.oppdater).setOnClickListener(View -> {

                new AlertDialog.Builder(adapter.context)
                        .setMessage(oppdatermsg)
                        .setCancelable(false)
                        .setPositiveButton(oppdaterJA, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // lager en intent for å sende iden til personen som skal oppdateres så går vi til oppdaterings siden
                                Intent intent = new Intent(adapter.context, OppdaterVenner.class);
                                intent.putExtra("id",adapter.venner.get(getAdapterPosition()).getId());
                                adapter.notifyItemChanged(getAdapterPosition());
                                adapter.context.startActivity(intent);

                            }
                        })
                        .setNegativeButton(oppdaterNei, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

            });
        }

        public MyViewHolder linkAdpater(Venner_adapter adapter){
            this.adapter = adapter;
            return this;
        }

    }
}
