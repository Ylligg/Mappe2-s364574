package com.example.mappe2_s364574;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Møte_adapter extends RecyclerView.Adapter<Møte_adapter.MyViewHolder> {
    Context context;
    ArrayList<Avtale> møter;
    public Møte_adapter(Context context, ArrayList<Avtale> møter){
        this.context = context;
        this.møter = møter;
    }
    @NonNull
    @Override
    public Møte_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new Møte_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Møte_adapter.MyViewHolder holder, int position) {
        holder.navn.setText(møter.get(position).navnpåPerson);
        holder.dato.setText(møter.get(position).datoforMøte);
        holder.klokke.setText(møter.get(position).klokkeslettforMøte);
        holder.sted.setText(møter.get(position).møtested);

    }

    @Override
    public int getItemCount() {
        return møter.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView navn,dato,klokke,sted;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            navn = itemView.findViewById(R.id.navn);
            dato = itemView.findViewById(R.id.dato);
            klokke = itemView.findViewById(R.id.klokke);
            sted = itemView.findViewById(R.id.sted);
        }
    }
}
