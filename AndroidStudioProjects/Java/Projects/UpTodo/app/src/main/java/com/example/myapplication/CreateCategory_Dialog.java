package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CreateCategory_Dialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_category_dialog, null);
        builder.setView(view)
                .setTitle("Create new Category")
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .setPositiveButton("Create Category", (dialog, which) -> {

                });
        return builder.create();
    }
}
