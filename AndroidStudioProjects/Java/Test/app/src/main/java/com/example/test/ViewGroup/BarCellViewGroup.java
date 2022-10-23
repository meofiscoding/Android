package com.example.test.ViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.test.Model.MyGanttItem;
import com.example.test.R;

import java.util.List;

import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

public class BarCellViewGroup extends FrameLayout implements TableFixHeaderAdapter.BodyBinder<List<String>> {

    private ConstraintLayout card_item;
    private Context context;
    private List<MyGanttItem> myGanttItems;

    public BarCellViewGroup(@NonNull Context context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.card_view_item, this, true);
        card_item = (ConstraintLayout) findViewById(R.id.card_item);
    }

    public BarCellViewGroup(@NonNull Context context,List<MyGanttItem> myGanttItems) {
        super(context);
        this.context = context;
        this.myGanttItems = myGanttItems;
        LayoutInflater.from(context).inflate(R.layout.card_view_item, this, true);
        card_item = (ConstraintLayout) findViewById(R.id.card_item);
    }

    @Override
    public void bindBody(List<String> strings, int i, int i1) {
        if (strings.get(i1).equals("pending")) {
            card_item.setBackgroundColor(ContextCompat.getColor(context,R.color.yellow_300));
        }else if (strings.get(i1).equals("done")){
            card_item.setBackgroundColor(ContextCompat.getColor(context,R.color.green_200));
        }else{
            card_item.setBackgroundResource(R.drawable.border);
        }
    }
}
