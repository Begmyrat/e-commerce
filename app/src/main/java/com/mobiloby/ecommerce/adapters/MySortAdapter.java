package com.mobiloby.ecommerce.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiloby.ecommerce.R;

import java.util.ArrayList;

public class MySortAdapter extends RecyclerView.Adapter<MySortAdapter.ViewHolder> {

    private ArrayList<String> list;
    private LayoutInflater mInflater;
    private ItemClickListenerSort mClickListener;
    private Context context;

    // data is passed into the constructor
    public MySortAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_sort, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.t_title.setText(list.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return list.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView t_title;
        View view;

        ViewHolder(View itemView) {
            super(itemView);

            t_title = itemView.findViewById(R.id.t_title);

            t_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    t_title.setBackgroundColor(context.getResources().getColor(R.color.colorPink));
                    t_title.setTextColor(context.getResources().getColor(R.color.white));
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition(), list);
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return list.get(id);
    }

    // allows clicks events to be caught
    public void setClickListenerSort(ItemClickListenerSort itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListenerSort {
        void onItemClick(View view, int position, ArrayList<String> list);
    }
}

