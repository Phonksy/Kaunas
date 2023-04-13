package com.example.kaunas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList pavadinimai;
    private ArrayList adresai;
    private ArrayList vertinimai;

    public MyAdapter(Context context, ArrayList pavadinimai, ArrayList adresai, ArrayList vertinimai)
    {
        this.context=context;
        this.pavadinimai=pavadinimai;
        this.adresai=adresai;
        this.vertinimai=vertinimai;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.hotel_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(pavadinimai.get(position)));
        holder.address_id.setText(String.valueOf(adresai.get(position)));
        holder.rating_id.setText(String.valueOf(vertinimai.get(position)));
    }

    @Override
    public int getItemCount() {
        return pavadinimai.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_id, address_id, rating_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            address_id = itemView.findViewById(R.id.textaddress);
            rating_id=itemView.findViewById(R.id.textrating);
        }
    }
}
