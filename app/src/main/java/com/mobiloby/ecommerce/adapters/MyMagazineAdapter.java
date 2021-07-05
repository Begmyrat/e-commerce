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

public class MyMagazineAdapter extends RecyclerView.Adapter<MyMagazineAdapter.ViewHolder> {

    private ArrayList<String> list;
    private LayoutInflater mInflater;
    private ItemClickListenerSub mClickListener;
    private Context context;
    public static int lastIndex = 0;

    // data is passed into the constructor
    public MyMagazineAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.lastIndex = 0;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_magazine, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.t_title.setText(list.get(position));

        if(position!=lastIndex) {
            holder.view.setVisibility(View.INVISIBLE);
            holder.t_title.setTextColor(context.getResources().getColor(R.color.black));
            holder.t_title.setTypeface(null, Typeface.NORMAL);
            holder.t_title.setShadowLayer(0f, -1, 1, R.color.white);
        }
        else {
            holder.view.setVisibility(View.VISIBLE);
            holder.t_title.setTextColor(context.getResources().getColor(R.color.colorPink));
            holder.t_title.setTypeface(null, Typeface.BOLD);
            holder.t_title.setShadowLayer(1f, -1, 1, R.color.colorPink);
        }

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
            view = itemView.findViewById(R.id.view);

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
    public void setClickListenerSub(ItemClickListenerSub itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListenerSub {
        void onItemClick(View view, int position, ArrayList<String> list);
    }
}

