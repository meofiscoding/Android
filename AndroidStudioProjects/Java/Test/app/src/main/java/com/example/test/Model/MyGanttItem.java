package com.example.test.Model;

import android.graphics.Point;

public class MyGanttItem {
    private String taskName;
    private boolean isError;
    private boolean isEmpty;
    private Point point;

    public MyGanttItem(String taskName, boolean isError,Point point) {
        //Constructor have "task name","point", and "isError?"
        this.taskName = taskName;
        this.isError = isError;
        this.point = point;
    }

    public MyGanttItem(String taskName, boolean isEmpty) {
        //Constructor have "task name","point", and "isError?"
        this.taskName = taskName;
        this.isEmpty = isEmpty;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
