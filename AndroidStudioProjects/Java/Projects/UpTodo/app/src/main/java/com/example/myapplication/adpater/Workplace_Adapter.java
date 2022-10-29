package com.example.myapplication.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Workplace;

import java.util.List;

public class Workplace_Adapter extends RecyclerView.Adapter<Workplace_Adapter.WorlplaceHolder> {
  private List<Workplace> workplaceList;
  public Workplace_Adapter(List<Workplace> workplaceList){
      this.workplaceList=workplaceList;
  }
    @NonNull
    @Override
    public WorlplaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_workplace,parent,false);

      return new WorlplaceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorlplaceHolder holder, int position) {
    Workplace workplace=workplaceList.get(position);
     if(workplace==null){
         return;
     }
     holder.txtname.setText(workplace.getWorkplace_name());
     holder.txtid.setText(workplace.getWorkplace_id());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WorlplaceHolder extends RecyclerView.ViewHolder{
        private TextView txtid,txtname;

        public WorlplaceHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.id);
            txtname=itemView.findViewById(R.id.name);
        }
    }
}
