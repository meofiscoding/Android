package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Task;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView completeTask;
    private FirebaseFirestore db;
    private Group emptyTask;
    private Group listTask;
    private EditText searchBar;
    private boolean incompleteEmpty;
    private boolean completeEmpty;
    private String search;
    private Query optionIncomplete;
    private Query optionComplete;
    private List<Task> taskArrayList;
    private List<Task> completeTaskArrayList;

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
        //Set layout manager for Fragment view
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        completeTask.setLayoutManager(new LinearLayoutManager(root.getContext()));

//        tasksRef = FirebaseDatabase.getInstance("https://uptodo-122bf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Task");
        db = FirebaseFirestore.getInstance();
        taskArrayList = new ArrayList<>();
        completeTaskArrayList = new ArrayList<>();
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
        if (!incompleteEmpty || !completeEmpty) {
            emptyTask.setVisibility(View.GONE);
            listTask.setVisibility(View.VISIBLE);
        } else {
            emptyTask.setVisibility(View.VISIBLE);
            listTask.setVisibility(View.GONE);
        }
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        optionIncomplete = db.collection("Task").whereEqualTo("done", false);
        optionComplete = db.collection("Task").whereEqualTo("done", true);
        //Option for inComplete Task
        FirestoreRecyclerOptions<Task> options = new FirestoreRecyclerOptions.Builder<Task>()
                .setQuery(optionIncomplete, Task.class)
                .build();
//        //Option for Complete Task
        FirestoreRecyclerOptions<Task> optionsComplete = new FirestoreRecyclerOptions.Builder<Task>()
                .setQuery(optionComplete, Task.class)
                .build();


        //adapter part
        FirestoreRecyclerAdapter<Task, myViewHolder> adapter = new FirestoreRecyclerAdapter<Task, myViewHolder>(options) {

            @Override
            public void onDataChanged() {
                System.out.println("data incomplete changed");
            }

            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Task model) {
                holder.taskTittle.setText(model.getTask());
                holder.checkButton.setChecked(false);
                //Radio button onClick
                holder.checkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Task updateTask = taskArrayList.get(holder.getBindingAdapterPosition());
                        db.collection("Task")
                                .document(updateTask.getId())
                                .update("done", true)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        taskArrayList.remove(updateTask);
                                        Toast.makeText(holder.itemView.getContext(), "Task completed!!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error updating document", e);
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


        optionIncomplete.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w(TAG, "Listen failed.", error);
                    return;
                }

                for (QueryDocumentSnapshot doc : value) {
                    if (doc.get("done").toString() == "false") {
                        //Dosth
                    }
                }

                if (!value.isEmpty()) {
                    incompleteEmpty = false;
                    List<DocumentSnapshot> list = value.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Task task = d.toObject(Task.class);
                        task.setId(d.getId());
                        taskArrayList.add(task);
                        System.out.println("list incomplete size: " + taskArrayList.size());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    incompleteEmpty = true;
                }
            }
        });


//        Adapter for Complete Task
        FirestoreRecyclerAdapter<Task, myViewHolder> adapterComplete = new FirestoreRecyclerAdapter<Task, myViewHolder>(optionsComplete) {

            @Override
            public void onDataChanged() {
                System.out.println("data complete task changed");
            }

            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Task model) {
                holder.taskTittle.setText(model.getTask());
                holder.checkButton.setChecked(false);
                holder.checkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Task updateTask = completeTaskArrayList.get(holder.getBindingAdapterPosition());
                        db.collection("Task")
                                .document(updateTask.getId())
                                .update("done", false)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        completeTaskArrayList.remove(updateTask);
                                        Toast.makeText(holder.itemView.getContext(), "Task incompleted:(((", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error updating document", e);
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

        optionComplete.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w(TAG, "Listen failed.", error);
                    return;
                }

                for (QueryDocumentSnapshot doc : value) {
                    if (doc.get("done").toString() == "true") {
                        //Dosth
                    }
                }

                if (!value.isEmpty()) {
                    completeEmpty = false;
                    List<DocumentSnapshot> list = value.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Task task = d.toObject(Task.class);
                        task.setId(d.getId());
                        completeTaskArrayList.add(task);
                        System.out.println("list complete size: " + completeTaskArrayList.size());
                    }
                    adapterComplete.notifyDataSetChanged();
                } else {
                    completeEmpty = true;
                }
            }
        });


        //Incomplete task
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