package com.example.myapplication.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
@IgnoreExtraProperties
public class Board {
    @Exclude
    private int board_id;
    private String board_name;
    private @ServerTimestamp Date createdDate;
    private Workplace workplace;
    private int status;

    public Board() {
    }

    public Board(int board_id, String board_name, Date createdDate, Workplace workplace, int status) {
        this.board_id = board_id;
        this.board_name = board_name;
        this.createdDate = createdDate;
        this.workplace = workplace;
        this.status = status;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
