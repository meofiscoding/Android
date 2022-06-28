package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OnBoardingFragment3 extends Fragment {
    TextView skip;
    Button get_started;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding3, container, false);
        //Skip onclick
        skip = (TextView) root.findViewById(R.id.txt_skip);
        skip.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), StartActivity.class);
            startActivity(intent);
        });
        //Button onclick
        get_started = (Button) root.findViewById(R.id.start_button);
        get_started.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), StartActivity.class);
            startActivity(intent);
        });
        return root;
    }
}
