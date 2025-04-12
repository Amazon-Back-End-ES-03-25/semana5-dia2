package com.ironhack.semana5_dia2.dto;

import com.ironhack.semana5_dia2.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequestDTO {

    @NotBlank(message = "Book title cannot be null or blank")
    private String title;

    @NotBlank(message = "Book author cannot be null or blank")
    private String author;

    @NotNull(message = "Book category cannot be null")
    private Category category;

    @Min(value = 1, message = "Number of book pages must be greater than zero")
    private int pages;

    public @NotBlank(message = "Book title cannot be null or blank") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Book title cannot be null or blank") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Book author cannot be null or blank") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Book author cannot be null or blank") String author) {
        this.author = author;
    }

    public @NotNull(message = "Book category cannot be null") Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull(message = "Book category cannot be null") Category category) {
        this.category = category;
    }

    @Min(value = 1, message = "Number of book pages must be greater than zero")
    public int getPages() {
        return pages;
    }

    public void setPages(@Min(value = 1, message = "Number of book pages must be greater than zero") int pages) {
        this.pages = pages;
    }
}