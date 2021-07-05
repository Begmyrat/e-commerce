package com.mobiloby.ecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiloby.ecommerce.R;

import java.util.ArrayList;

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.ViewHolder> {

    private ArrayList<Integer> list;
    private LayoutInflater mInflater;
    private ItemClickListenerProducts mClickListener;
    private Context context;

    // data is passed into the constructor
    public MyProductAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;

    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_gridview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.image.setImageResource(list.get(position));

        if(position%2==1){
            holder.view.setVisibility(View.VISIBLE);
        }
        else{
            holder.view.setVisibility(View.GONE);
        }

        if(position==list.size()-1 || position==list.size()-2){

        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return list.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView t_policeNo, t_acente, t_start, t_end, t_price;
        View view;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.view_space);
            image = itemView.findViewById(R.id.i_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClickProduct(view, getAdapterPosition(), list);
        }
    }

    // convenience method for getting data at click position
    public Integer getItem(int id) {
        return list.get(id);
    }

    // allows clicks events to be caught
    public void setClickListenerProducts(ItemClickListenerProducts itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListenerProducts {
        void onItemClickProduct(View view, int position, ArrayList<Integer> list);
    }
}

