package com.ironhack.semana5_dia2.dto;

import com.ironhack.semana5_dia2.model.Category;
import jakarta.validation.constraints.Min;

public class BookUpdateRequestDTO {

    private String title;

    private String author;

    private Category category;

    @Min(value = 1, message = "Number of book pages must be greater than zero")
    private Integer pages;

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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
