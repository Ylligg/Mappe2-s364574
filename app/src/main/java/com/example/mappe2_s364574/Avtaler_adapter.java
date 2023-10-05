package com.example.mappe2_s364574;

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

    public static DataKildeVenner dataKilde;
    List<Venner> venner;


    public Venner_adapter(Context context, List<Venner> venner){
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

        long vennid=0;
        String vennNavn, venntlfnr;



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

                                vennid = adapter.venner.get(getAdapterPosition()).getId();
                                vennNavn = adapter.venner.get(getAdapterPosition()).getNavn();
                                venntlfnr = adapter.venner.get(getAdapterPosition()).getTelefonnummer();
                                dataKilde = new DataKildeVenner(adapter.context);
                                dataKilde.open();
                                dataKilde.slettVenn(vennid);

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

            itemView.findViewById(R.id.oppdater).setOnClickListener(View -> {

                new AlertDialog.Builder(adapter.context)
                        .setMessage("Sikker på å oppdatere?")
                        .setCancelable(false)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(adapter.context, OppdaterVenner.class);
                                intent.putExtra("id",adapter.venner.get(getAdapterPosition()).getId());
                                adapter.notifyItemChanged(getAdapterPosition());
                                adapter.context.startActivity(intent);

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
