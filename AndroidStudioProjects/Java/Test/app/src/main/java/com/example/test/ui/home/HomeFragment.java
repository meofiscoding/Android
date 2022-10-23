package com.example.test.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.databinding.*;
import com.inqbarna.tablefixheaders.TableFixHeaders;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    //Table fix header
    TableFixHeaders tableFixHeaders;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
    }

    private void bindingView(View view) {
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.tableFixTables);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}