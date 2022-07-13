package com.example.myapplication.data;

import com.example.myapplication.model.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TaskDAO {
    private DatabaseReference databaseReference;

    public TaskDAO() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://uptodo-122bf-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = db.getReference(Task.class.getSimpleName());
    }

    public com.google.android.gms.tasks.Task<Void> add(Task task) {
        return databaseReference.push().setValue(task);
    }
}
