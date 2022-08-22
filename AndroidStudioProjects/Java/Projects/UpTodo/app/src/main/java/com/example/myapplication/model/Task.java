package com.example.myapplication.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;
@IgnoreExtraProperties
// we have to implement our modal class
// with serializable so that we can pass
// our object class to new activity on
// our item click of recycler view.
public class Task implements Serializable {
    private String task;
    private Priority priority;
    private Date dueDate;
    private @ServerTimestamp Date createdDate;
    private boolean isDone;
    private Category category;

    // we are using exclude because
    // we are not saving our id
    @Exclude
    private String id;
    // getter method for our id
    public String getId() {
        return id;
    }

    // setter method for our id
    public void setId(String id) {
        this.id = id;
    }

    public Task() {
    }

    public Task(String task, Priority priority, Date dueDate, Date createdDate, boolean isDone, Category category) {
        this.task = task;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.isDone = isDone;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", task='" + task + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", createdDate=" + createdDate +
                ", isDone=" + isDone +
                ", category=" + category +
                '}';
    }


    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
