package com.example.myapplication.ui.index;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adpater.Workplace_Adapter;
import com.example.myapplication.model.Workplace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Workplace_recycle extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Workplace> workplaceList=new ArrayList<Workplace>();
    private Workplace_Adapter workplace_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplace_recycle);
        Workplace workplace1= new Workplace(1,"thinh");
        Workplace workplace2= new Workplace(2,"hello");
        workplaceList.add(workplace1);
        workplaceList.add(workplace2);
        recyclerView=findViewById(R.id.recylce);
        workplace_adapter= new Workplace_Adapter(workplaceList);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(workplace_adapter);
    }
}