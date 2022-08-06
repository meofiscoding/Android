package com.example.myapplication.model;

import java.util.Date;

public class Task {
    public String task;
    public String description;
    public Priority priority;
    public Date dueDate;
    public Date createdDate;
    public boolean isDone;
    public Category category;

    public Task() {
    }

    public Task(String task, String description, Priority priority, Date dueDate, Date createdDate, boolean isDone, Category category) {
        this.task = task;
        this.description = description;
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
                ", description='" + description + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
