package com.ironhack.semana5_dia2.dto;

import jakarta.validation.constraints.Min;

// Actualizaciones PARCIALES
public class ProductUpdateRequestDTO {
    private String name;
    private String category;

    @Min(value = 1, message = "Price must be greater than zero")
    private Integer price; // <--- como Integer no int para que pueda ser null

    public ProductUpdateRequestDTO() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
