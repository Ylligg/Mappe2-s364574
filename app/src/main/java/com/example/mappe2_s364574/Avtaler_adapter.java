package com.example.mappe2_s364574;






import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Avtaler_adapter extends RecyclerView.Adapter<Avtaler_adapter.MyViewHolder>{
    Context context;

    public static DataKildeAvtaler dataKilde;
    List<Avtale> avtaler;


    public Avtaler_adapter(Context context, List<Avtale> avtaler){
        this.context = context;
        this.avtaler = avtaler;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_avtaler, parent, false);

        return new MyViewHolder(view).linkAdpater(this);
    }

    @Override
    public void onBindViewHolder(@NonNull Avtaler_adapter.MyViewHolder holder, int position) {
        holder.navn.setText(avtaler.get(position).navnpåPerson);
        holder.dato.setText(avtaler.get(position).datoforMøte);
        holder.klokke.setText(avtaler.get(position).klokkeslettforMøte);
        holder.sted.setText(avtaler.get(position).møtested);

    }

    @Override
    public int getItemCount() {
        return avtaler.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView navn,dato,klokke,sted;
        private Avtaler_adapter adapter;

        long avtaleId=0;
        String avtaleNavn, avtaleDato, avtaleKlokke, avtaleSted;

        String slettmsg, slettJA, slettNei,oppdatermsg, oppdaterJA, oppdaterNei;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            navn = itemView.findViewById(R.id.navn);
            dato = itemView.findViewById(R.id.dato);
            klokke = itemView.findViewById(R.id.klokke);
            sted = itemView.findViewById(R.id.sted);


            slettmsg = itemView.getResources().getString(R.string.slettAvtaleSafeguard);
            slettJA = itemView.getResources().getString(R.string.slettJa);
            slettNei = itemView.getResources().getString(R.string.slettNei);


            itemView.findViewById(R.id.slettAvtale).setOnClickListener(View -> {

                new AlertDialog.Builder(adapter.context)
                        .setMessage(slettmsg)
                        .setCancelable(false)
                        .setPositiveButton(slettJA, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                avtaleId = adapter.avtaler.get(getAdapterPosition()).getId();
                                avtaleNavn = adapter.avtaler.get(getAdapterPosition()).getNavnpåPerson();
                                avtaleDato = adapter.avtaler.get(getAdapterPosition()).getDatoforMøte();
                                avtaleKlokke = adapter.avtaler.get(getAdapterPosition()).getKlokkeslettforMøte();
                                avtaleSted = adapter.avtaler.get(getAdapterPosition()).getMøtested();
                                dataKilde = new DataKildeAvtaler(adapter.context);
                                dataKilde.open();
                                dataKilde.slettAvtale(avtaleId);

                                adapter.avtaler.remove(getAdapterPosition());
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

            oppdatermsg = itemView.getResources().getString(R.string.oppdaterSafeguard);
            oppdaterJA = itemView.getResources().getString(R.string.oppdaterJa);
            oppdaterNei = itemView.getResources().getString(R.string.oppdaterNei);

            itemView.findViewById(R.id.oppdaterAvtale).setOnClickListener(View -> {

                new AlertDialog.Builder(adapter.context)
                        .setMessage(oppdatermsg)
                        .setCancelable(false)
                        .setPositiveButton(oppdaterJA, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(adapter.context, OppdaterAvtale.class);
                                intent.putExtra("id",adapter.avtaler.get(getAdapterPosition()).getId());
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

        public MyViewHolder linkAdpater(Avtaler_adapter adapter){
            this.adapter = adapter;
            return this;
        }

    }
}
