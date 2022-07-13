package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CategoryDialog extends AppCompatDialogFragment implements View.OnClickListener {
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    Button addCategory;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.task_category_dialog, null);
        builder.setView(view);
        addCategory = view.findViewById(R.id.CreateNewBtn);
        addCategory.setOnClickListener(v -> {
            CreateCategory_Dialog createCategory_dialog = new CreateCategory_Dialog();
            createCategory_dialog.show(getActivity().getSupportFragmentManager(),"Create new Category");
        });
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        String categoryName;
        switch (v.getId()) {
            case R.id.grocery:
                categoryName = "Grocery";
                break;
            case R.id.work:
                categoryName = "Work";
                break;
            case R.id.sport:
                categoryName = "Sport";
                break;
            case R.id.design:
                categoryName = "Design";
                break;
            case R.id.university:
                categoryName = "University";
                break;
            case R.id.social:
                categoryName = "Social";
                break;
            case R.id.music:
                categoryName = "Music";
                break;
            case R.id.health:
                categoryName = "Health";
                break;
            case R.id.movie:
                categoryName = "Movie";
                break;
            case R.id.home:
                categoryName = "Home";
                break;
            case R.id.createnew:

                break;
        }
    }
}
