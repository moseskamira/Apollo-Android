package com.example.apolloandroid.models;

public class MyBook {
    String title;
    String isbn;

    public MyBook() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "MyBook{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

}
