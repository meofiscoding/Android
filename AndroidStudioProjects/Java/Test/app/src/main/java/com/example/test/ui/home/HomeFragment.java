package com.example.test.ui.home;

import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.Adapter.MyTableFixHeaderAdapter;
import com.example.test.Common.Common;
import com.example.test.Model.MyGanttItem;
import com.example.test.R;
import com.example.test.databinding.FragmentHomeBinding;
import com.inqbarna.tablefixheaders.TableFixHeaders;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    //Table fix header
    TableFixHeaders tableFixHeaders;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        createGantChart(view);
    }

    private void createGantChart(View view) {
        List<MyGanttItem> myGanttItemsList = new ArrayList<>();
        MyGanttItem row1 = new MyGanttItem("Requirement", false, new Point(0, 3));
        MyGanttItem row2 = new MyGanttItem("Design", false, new Point(5, 10));

        myGanttItemsList.add(row1);
        myGanttItemsList.add(row2);

        myGanttItemsList.add(new MyGanttItem("Coding", true));
        myGanttItemsList.add(new MyGanttItem("Testing", true));
        myGanttItemsList.add(new MyGanttItem("Maintance", true, new Point(6, 7)));

        MyTableFixHeaderAdapter adapter = new MyTableFixHeaderAdapter(view.getContext(), myGanttItemsList);

        List<List<String>> body = getBody(myGanttItemsList);
        adapter.setFirstHeader("Task name");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        tableFixHeaders.setAdapter(adapter );
    }

    private List<String> getHeader() {
        List<String> header = new ArrayList<>();
        for (int i = 0; i<= Common.HEADER_COUNT; i++){
            header.add(new StringBuilder().append(i).toString());
        }
        return header;
    }

    private List<List<String>> getBody(List<MyGanttItem> myGanttItemsList) {
        List<List<String>> rows = new ArrayList<>();
        for (MyGanttItem myGanttItem : myGanttItemsList) {
            List<String> cols = new ArrayList<>();
            if (!myGanttItem.isEmpty()) {
                for (int col = 0; col < Common.COLLUMN_COUNT; col++) {
                    if (col >= myGanttItem.getPoint().x && col <= myGanttItem.getPoint().y) {
                        if (myGanttItem.isError()) {
                            cols.add("error");
                        } else {
                            cols.add("done");
                        }
                    } else {
                        cols.add("default");
                    }
                }
            } else {
                for (int col = 0; col < Common.COLLUMN_COUNT; col++) {
                    cols.add("default");
                }
            }
            rows.add(cols);
        }
        return rows;
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