package com.example.myapplication.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;
@IgnoreExtraProperties
public class Workplace {
    @Exclude
    private int workplace_id;
    private String workplace_name;
    private @ServerTimestamp Date createdDate;
    private int status;

    public Workplace() {
    }

    public Workplace(int workplace_id, String workplace_name) {
        this.workplace_id = workplace_id;
        this.workplace_name = workplace_name;
    }

    public Workplace(int workplace_id, String workplace_name, Date createdDate, int status) {
        this.workplace_id = workplace_id;
        this.workplace_name = workplace_name;
        this.createdDate = createdDate;
        this.status = status;
    }

    public int getWorkplace_id() {
        return workplace_id;
    }

    public void setWorkplace_id(int workplace_id) {
        this.workplace_id = workplace_id;
    }

    public String getWorkplace_name() {
        return workplace_name;
    }

    public void setWorkplace_name(String workplace_name) {
        this.workplace_name = workplace_name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
