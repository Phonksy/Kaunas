package com.example.kaunas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class hotelsAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<String> items;

    public hotelsAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item, parent, false);
        return new ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView1.setText(items.get(position));
        holder.textView2.setText(items.get(position));
        holder.textView3.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView1;
    TextView textView2;
    TextView textView3;

    private hotelsAdapter adapter;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        textView1 = itemView.findViewById(R.id.pavadinimas);
        textView2 = itemView.findViewById(R.id.adresas);
        textView3 = itemView.findViewById(R.id.vertinimas);

    }

    public ViewHolder linkAdapter(hotelsAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}

