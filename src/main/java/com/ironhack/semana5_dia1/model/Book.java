package com.ironhack.semana5_dia1.model;

public class Book {
    private Long id;
    private String title;
    private String author;
    private Category category;
    private int pages;

    public Book() {
    }

    public Book(Long id, String title, String author, Category category, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
