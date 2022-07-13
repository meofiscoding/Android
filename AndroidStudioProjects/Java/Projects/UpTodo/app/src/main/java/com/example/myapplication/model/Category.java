package com.example.myapplication.model;

public class Category {
    public String categoryName;

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
