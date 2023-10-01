package com.example.mappe2_s364574;

import static android.content.Intent.getIntent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Venner_adapter extends RecyclerView.Adapter<Venner_adapter.MyViewHolder>{
    Context context;

    ArrayList<Venner> venner;
    public Venner_adapter(Context context, ArrayList<Venner> venner){
        this.context = context;
        this.venner = venner;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_venner, parent, false);
        return new MyViewHolder(view).linkAdpater(this);
    }

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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            navn = itemView.findViewById(R.id.navn);
            tlf = itemView.findViewById(R.id.tlfnr);
            itemView.findViewById(R.id.slett).setOnClickListener(View -> {

                new AlertDialog.Builder(adapter.context)
                        .setMessage("Sikker på å slette denne personen?")
                        .setCancelable(false)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.venner.remove(getAdapterPosition());
                                adapter.notifyItemRemoved(getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Nei", new DialogInterface.OnClickListener() {
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
