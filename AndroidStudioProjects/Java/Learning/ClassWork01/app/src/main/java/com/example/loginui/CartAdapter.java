package com.example.loginui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Category;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private List<Category> categories;
    private Context context;

    public CartAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imv.setImageResource(categories.get(position).getId_drawable());
        holder.txtPrice.setText("40$");
        holder.txtName.setText(categories.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imv;
        TextView txtName;
        TextView txtPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imv = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
