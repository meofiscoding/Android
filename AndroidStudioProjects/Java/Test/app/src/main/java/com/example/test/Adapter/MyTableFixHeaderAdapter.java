package com.example.test.Adapter;

import android.content.Context;

import com.example.test.Common.Common;
import com.example.test.Model.MyGanttItem;
import com.example.test.R;
import com.example.test.ViewGroup.BarCellViewGroup;
import com.example.test.ViewGroup.DefaultCellViewGroup;

import java.util.ArrayList;
import java.util.List;

import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

public class MyTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, DefaultCellViewGroup, //First Header
        String, DefaultCellViewGroup,  //First Body
        List<String>, //DataSource
        DefaultCellViewGroup, //First body
        BarCellViewGroup, //Body item
        DefaultCellViewGroup //Section
        > {

    private Context context;
    private List<MyGanttItem> myGanttItems;

    public MyTableFixHeaderAdapter(Context context, List<MyGanttItem> myGanttItems) {
        super(context);
        this.context = context;
        this.myGanttItems = myGanttItems;
    }

    @Override
    protected DefaultCellViewGroup inflateFirstHeader() {
        return new DefaultCellViewGroup(context);
    }

    @Override
    protected DefaultCellViewGroup inflateHeader() {
        return new DefaultCellViewGroup(context);
    }

    @Override
    protected DefaultCellViewGroup inflateFirstBody() {
        return new DefaultCellViewGroup(context, myGanttItems);
    }

    @Override
    protected BarCellViewGroup inflateBody() {
        return new BarCellViewGroup(context, myGanttItems);
    }

    @Override
    protected DefaultCellViewGroup inflateSection() {
        return new DefaultCellViewGroup(context);
    }

    @Override
    protected List<Integer> getHeaderWidths() {
        List<Integer> headerWidth = new ArrayList<>();
        headerWidth.add((int)context.getResources().getDimension(R.dimen._150dp));
        for (int i = 0; i < Common.COLLUMN_COUNT; i++) {
            headerWidth.add((int)context.getResources().getDimension(R.dimen._40dp));
        }
        return headerWidth;
    }

    @Override
    protected int getHeaderHeight() {
        return (int)context.getResources().getDimension(R.dimen._40dp);
    }

    @Override
    protected int getSectionHeight() {
        return (int)context.getResources().getDimension(R.dimen._40dp);
    }

    @Override
    protected int getBodyHeight() {
        return (int)context.getResources().getDimension(R.dimen._40dp);
    }

    @Override
    protected boolean isSection(List list, int i) {
        return false;
    }
}
