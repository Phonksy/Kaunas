package com.example.kaunas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List viesbuciai;

    public MyAdapter(Context context, List viesbuciai)
    {
        this.context=context;
        this.viesbuciai=viesbuciai;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.hotel_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Hotel obj = (Hotel) viesbuciai.get(position);
        holder.name_id.setText(String.valueOf(obj.getPavadinimas()));
        holder.address_id.setText(String.valueOf(obj.getAdresas()));
        holder.rating_id.setText(String.valueOf(obj.getVertinimas()));
        holder.svetaine.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoUrl(obj.getSvetaine());
                    }
                });
        holder.dalintis.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(Intent.ACTION_SEND);
                        myIntent.setType("text/plain");
                        String shareBody = obj.getSvetaine();
                        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        context.startActivity(Intent.createChooser(myIntent, "Share using"));
                    }
                }
        );

        holder.foto.setImageResource(obj.getFoto());
        holder.nuorodos.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoUrl(obj.getNuorodos());
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return viesbuciai.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_id, address_id, rating_id;
        Button svetaine, dalintis, nuorodos;
        ImageView foto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            address_id = itemView.findViewById(R.id.textaddress);
            rating_id=itemView.findViewById(R.id.textrating);
            svetaine =itemView.findViewById(R.id.eiti);
            dalintis =itemView.findViewById(R.id.dalintis);
            foto = itemView.findViewById(R.id.nuotrauka);
            nuorodos=itemView.findViewById(R.id.nuorodos);
        }
    }

    private void gotoUrl(String s) {
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(s));
        context.startActivity(intent);
    }

}
