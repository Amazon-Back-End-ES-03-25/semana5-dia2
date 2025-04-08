package com.ironhack.semana5_dia1.dto;

public class ProductResponseDTO {
    private Long id;
    private String name;
    private String category;
    private int price;
    private String message;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, String name, String category, int price, String message) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
