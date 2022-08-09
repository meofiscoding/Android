package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Task;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView completeTask;
    private DatabaseReference tasksRef;
    private Group emptyTask;
    private Group listTask;
    private EditText searchBar;
    private boolean incompleteEmpty;
    private boolean completeEmpty;
    private String search;
    private Query optionIncomplete;
    private Query optionComplete;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        //Get recycler task View
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView2);
        completeTask = (RecyclerView) root.findViewById(R.id.complete_taskList);
        emptyTask = (Group) root.findViewById(R.id.emptyTask);
        listTask = (Group) root.findViewById(R.id.task_list);
        if (incompleteEmpty && completeEmpty) {
            emptyTask.setVisibility(View.VISIBLE);
            listTask.setVisibility(View.GONE);
        }
        //Set layout manager for Fragment view
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        completeTask.setLayoutManager(new LinearLayoutManager(root.getContext()));
        tasksRef = FirebaseDatabase.getInstance("https://uptodo-122bf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Task");
        searchBar = (EditText) root.findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        optionIncomplete = tasksRef.orderByChild("isDone").equalTo(false);
        optionComplete = tasksRef.orderByChild("isDone").equalTo(true);
        if (search != null) {
            optionIncomplete = optionIncomplete.orderByChild("task").startAt(search).endAt(search+"\uf8ff");
            optionComplete = optionComplete.orderByChild("task").startAt(search).endAt(search+"\uf8ff");
        }

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(optionIncomplete, Task.class)
                .build();

        //Option for Complete Task
        FirebaseRecyclerOptions<Task> optionsComplete = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(optionComplete, Task.class)
                .build();

        FirebaseRecyclerAdapter<Task, myViewHolder> adapter = new FirebaseRecyclerAdapter<Task, myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Task model) {
                tasksRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            emptyTask.setVisibility(View.GONE);
                            listTask.setVisibility(View.VISIBLE);
                            //do sth
                        } else {
                            incompleteEmpty = true;
                        }
                        holder.checkButton.setChecked(false);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                holder.taskTittle.setText(model.getTask());
                holder.checkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("isDone", true);
                        FirebaseDatabase.getInstance("https://uptodo-122bf-default-rtdb.asia-southeast1.firebasedatabase.app")
                                .getReference("Task")
                                .child(getRef(holder.getAdapterPosition()).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.itemView.getContext(), "Task completed!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
//                holder.categoryName.setText(model.getCategory().categoryName);
//                holder.timeStamp.setText(DateHumanizer.humanize(model.getDueDate(), DateHumanizer.TYPE_PRETTY_EVERYTHING));
                //humanizer dateTime
                //holder.timastamp
            }

            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtask_row, parent, false);
                myViewHolder viewHolder = new myViewHolder(view);
                return viewHolder;
            }
        };

        //Adapter for Complete Task
        FirebaseRecyclerAdapter<Task, myViewHolder> adapterComplete = new FirebaseRecyclerAdapter<Task, myViewHolder>(optionsComplete) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Task model) {
                tasksRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            emptyTask.setVisibility(View.GONE);
                            listTask.setVisibility(View.VISIBLE);
                        } else {
                            completeEmpty = true;
                        }
                        holder.checkButton.setChecked(false);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                holder.taskTittle.setText(model.getTask());

                holder.checkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("isDone", false);
                        FirebaseDatabase.getInstance("https://uptodo-122bf-default-rtdb.asia-southeast1.firebasedatabase.app")
                                .getReference("Task")
                                .child(getRef(holder.getAdapterPosition()).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.itemView.getContext(), "Task incompleted:(", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
//                holder.categoryName.setText(model.getCategory().categoryName);
//                holder.timeStamp.setText(DateHumanizer.humanize(model.getDueDate(), DateHumanizer.TYPE_PRETTY_EVERYTHING));
                //humanizer dateTime
                //holder.timastamp
            }

            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtask_row, parent, false);
                myViewHolder viewHolder = new myViewHolder(view);
                return viewHolder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

        //CompleteTask
        completeTask.setAdapter(adapterComplete);
        adapterComplete.startListening();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        RadioButton checkButton;
        TextView taskTittle;
        TextView timeStamp;
        ImageView priority_flag;
        Chip categoryName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            checkButton = (RadioButton) itemView.findViewById(R.id.check_button);
            taskTittle = (TextView) itemView.findViewById(R.id.taskName);
            timeStamp = (TextView) itemView.findViewById(R.id.endDate);
            priority_flag = (ImageView) itemView.findViewById(R.id.priority_flag);
            categoryName = (Chip) itemView.findViewById(R.id.categoryName);
        }

    }
}