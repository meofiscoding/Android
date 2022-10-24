package com.example.retrofitdemo;

public class Post {
    private String tittle;
    private String body;

    public Post(String tittle, String body) {
        this.tittle = tittle;
        this.body = body;
    }

    public String getTittle() {
        return tittle;
    }

    public String getBody() {
        return body;
    }
}
