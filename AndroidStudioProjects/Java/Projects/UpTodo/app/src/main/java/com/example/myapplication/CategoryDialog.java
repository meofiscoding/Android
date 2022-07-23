package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CategoryDialog extends DialogFragment implements android.view.View.OnClickListener {
    private Button getAddCategory;
    private Context c;
    private OnMyDialogResult mDialogResult; // the callback

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.task_category_dialog, null);
        builder.setView(view);
        getAddCategory = (Button) view.findViewById(R.id.CreateNewBtn);
        getAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(v.getContext(), New_Category.class);
            v.getContext().startActivity(intent);
        });
        ImageView imageView1 = view.findViewById(R.id.grocery);
        ImageView imageView2 = view.findViewById(R.id.work);
        ImageView imageView3 = view.findViewById(R.id.sport);
        ImageView imageView4 = view.findViewById(R.id.design);
        ImageView imageView5 = view.findViewById(R.id.university);
        ImageView imageView6 = view.findViewById(R.id.social);
        ImageView imageView7 = view.findViewById(R.id.music);
        ImageView imageView8 = view.findViewById(R.id.health);
        ImageView imageView9 = view.findViewById(R.id.movie);
        ImageView imageView10 = view.findViewById(R.id.home);
        ImageView imageView11 = view.findViewById(R.id.createnew);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
        imageView7.setOnClickListener(this);
        imageView8.setOnClickListener(this);
        imageView9.setOnClickListener(this);
        imageView10.setOnClickListener(this);
        imageView11.setOnClickListener(this);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        String categoryName = "";
        switch (v.getId()) {
            case R.id.grocery:
                categoryName = "Grocery";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.work:
                categoryName = "Work";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.sport:
                categoryName = "Sport";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.design:
                categoryName = "Design";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.university:
                categoryName = "University";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.social:
                categoryName = "Social";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.music:
                categoryName = "Music";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.health:
                categoryName = "Health";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.movie:
                categoryName = "Movie";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.home:
                categoryName = "Home";
                mDialogResult.finish(categoryName);
                this.dismiss();
                break;
            case R.id.createnew:
                Intent intent = new Intent();
                intent.setClass(v.getContext(), New_Category.class);
                v.getContext().startActivity(intent);
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mDialogResult = (OnMyDialogResult) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must be implement OnMyDialogResult");
        }
    }

    public interface OnMyDialogResult {
        void finish(String result);
    }

}
