package com.example.loginui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private List<Category> categories;
    private Context context;
    private Category category;
    private IClickAddToCartListener iClickAddToCartListener;

    public interface IClickAddToCartListener {
        void onClickAddToCart(ImageView imgAddToCart, Category category);
    }

    public void setData(List<Category> list, IClickAddToCartListener listener) {
        this.categories = list;
        this.iClickAddToCartListener = listener;
        notifyDataSetChanged();
    }

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
        category = categories.get(position);
        holder.img_cate.setImageResource(category.getId_drawable());
        holder.txt_title.setText(category.getTitle());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_cate;
        TextView txt_title;
        ImageView img_cart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cate = itemView.findViewById(R.id.img_category);
            txt_title = itemView.findViewById(R.id.txt_cateName);
            img_cate.setOnClickListener(this::onItemClick);
            txt_title.setOnClickListener(this::onItemClick);
            img_cart.setOnClickListener(this::onCartCheckout);
        }

        private void onCartCheckout(View view) {
            iClickAddToCartListener.onClickAddToCart(img_cate, categories.get(getBindingAdapterPosition()));
        }

        private void onItemClick(View view) {
            Intent intent = new Intent(context, CategoryDetail.class);
            intent.putExtra("category", new Category(categories.get(getBindingAdapterPosition()).getTitle(), category.getId_drawable()));
            context.startActivity(intent);
        }
    }
}
