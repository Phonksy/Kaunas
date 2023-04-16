package com.example.kaunas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {
    private Context context;
    private ArrayList nameID, feedbackID;

    public FeedbackAdapter(Context context, ArrayList nameID, ArrayList feedbackID) {
        this.context=context;
        this.nameID=nameID;
        this.feedbackID=feedbackID;
    }

    public FeedbackViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feedback_entry, parent, false);
        return new FeedbackViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        holder.nameID.setText(String.valueOf(nameID.get(position)));
        holder.feedbackID.setText(String.valueOf(feedbackID.get(position)));
    }

    @Override
    public int getItemCount() {
        return nameID.size();
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {

        TextView nameID, feedbackID;
        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            nameID=itemView.findViewById(R.id.nameText);
            feedbackID=itemView.findViewById(R.id.feedbackText);
        }
    }
}
