package com.example.mappe2_s364574;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public Venner_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_venner, parent, false);
        return new Venner_adapter.MyViewHolder(view);
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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            navn = itemView.findViewById(R.id.navn);
            tlf = itemView.findViewById(R.id.tlfnr);

        }
    }
}
