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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{
    private List<Category> categories;
    private Context context;

    public CategoryAdapter(List<Category> chapters, Context context) {
        this.categories = chapters;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        holder.img_cate.setImageResource(categories.get(position).getId_drawable());
        holder.txt_title.setText(categories.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_cate;
        TextView txt_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cate = itemView.findViewById(R.id.img_category);
            txt_title = itemView.findViewById(R.id.txt_cateName);
        }
    }
}
