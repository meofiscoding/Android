package com.example.test.ViewGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.test.Model.MyGanttItem;
import com.example.test.R;

import java.util.List;

import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

public class DefaultCellViewGroup extends FrameLayout implements
        TableFixHeaderAdapter.FirstBodyBinder<List<String>>,
        TableFixHeaderAdapter.FirstHeaderBinder<String>,
        TableFixHeaderAdapter.HeaderBinder<String>,
        TableFixHeaderAdapter.BodyBinder<List<String>>,
        TableFixHeaderAdapter.SectionBinder<List<String>> {

    private TextView txt_content;
    private List<MyGanttItem> myGanttItemsList;

    public DefaultCellViewGroup(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.text_view_item, this,true);
        txt_content = (TextView) findViewById(R.id.txt_content);
    }

    public DefaultCellViewGroup(@NonNull Context context, List<MyGanttItem> myGanttItemsList) {
        super(context);
        this.myGanttItemsList = myGanttItemsList;
        LayoutInflater.from(context).inflate(R.layout.text_view_item, this, true);
        txt_content = (TextView) findViewById(R.id.txt_content);
    }

    @Override
    public void bindFirstHeader(String s) {
        txt_content.setText(s);
    }

    @Override
    public void bindBody(List<String> strings, int i, int i1) {
        txt_content.setText(strings.get(i1));
    }

    @Override
    public void bindSection(List<String> strings, int i, int i1) {
        txt_content.setText(i == 0? "Section: " + (i+1) : "0");
    }

    @Override
    public void bindHeader(String s, int i) {
        txt_content.setText(s);
    }

    @Override
    public void bindFirstBody(List<String> strings, int i) {
        txt_content.setText(myGanttItemsList.get(i).getTaskName());
    }

}
