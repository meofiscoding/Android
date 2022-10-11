package com.example.musicservice;

import java.io.Serializable;

public class Song implements Serializable {
    private String tittle;
    private int image;
    private int resourceMedia;

    public Song(String tittle, int image, int resourceMedia) {
        this.tittle = tittle;
        this.image = image;
        this.resourceMedia = resourceMedia;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getResourceMedia() {
        return resourceMedia;
    }

    public void setResourceMedia(int resourceMedia) {
        this.resourceMedia = resourceMedia;
    }
}
