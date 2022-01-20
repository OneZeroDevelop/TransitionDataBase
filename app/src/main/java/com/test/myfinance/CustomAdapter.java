package com.test.myfinance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context context;
    private ArrayList trans_id, trans_sum, trans_name;

    CustomAdapter(Context context,
                  ArrayList trans_id,
                  ArrayList trans_sum,
                  ArrayList trans_name){
            this.context = context;
            this.trans_id = trans_id;
            this.trans_sum = trans_sum;
            this.trans_name = trans_name;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.trans_id_text.setText(String.valueOf(trans_id.get(position)));
        holder.trans_name_text.setText(String.valueOf(trans_name.get(position)));
        holder.trans_sum_text.setText(String.valueOf(trans_sum.get(position)));
    }

    @Override
    public int getItemCount() {
        return trans_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView trans_id_text, trans_sum_text, trans_name_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trans_id_text = itemView.findViewById(R.id.trans_id_text);
            trans_sum_text = itemView.findViewById(R.id.trans_sum_text);
            trans_name_text = itemView.findViewById(R.id.trans_name_text);
        }
    }
}
