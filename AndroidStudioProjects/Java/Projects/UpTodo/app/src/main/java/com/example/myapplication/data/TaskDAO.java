package com.example.myapplication.data;

import com.example.myapplication.model.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TaskDAO {
    private DocumentReference documentReference;
    private FirebaseFirestore db;
    public TaskDAO() {
        db = FirebaseFirestore.getInstance();
        documentReference = db.collection("Task").document();
//        FirebaseDatabase db = FirebaseDatabase.getInstance("https://uptodo-122bf-default-rtdb.asia-southeast1.firebasedatabase.app/");
//        databaseReference = db.getReference(Task.class.getSimpleName());
    }

    public com.google.android.gms.tasks.Task<Void> add(Task task) {
        return documentReference.set(task);
    }
}
