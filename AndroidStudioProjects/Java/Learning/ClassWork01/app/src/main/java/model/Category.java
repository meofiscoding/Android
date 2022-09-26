package model;

public class Category {
    private String title;
    private int id_drawable;

    public Category(String title, int id_drawable) {
        this.title = title;
        this.id_drawable = id_drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_drawable() {
        return id_drawable;
    }

    public void setId_drawable(int id_drawable) {
        this.id_drawable = id_drawable;
    }
}
