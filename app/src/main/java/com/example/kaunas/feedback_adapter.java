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

public class feedback_adapter extends RecyclerView.Adapter<feedback_adapter.MyViewHolder> {

    private Context context;
    private List duomenys;

    public feedback_adapter(Context context, List duomenys)
    {
        this.context = context;
        this.duomenys = duomenys;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        atsiliepimas obj = (atsiliepimas) duomenys.get(position);
        holder.vardas.setText(String.valueOf(obj.getVardas()));
        holder.komentaras.setText(String.valueOf(obj.getKomentaras()));
    }

    @Override
    public int getItemCount() {
        return duomenys.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView vardas, komentaras;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vardas = itemView.findViewById(R.id.vardas);
            komentaras = itemView.findViewById(R.id.komentaras);
        }
    }

}
