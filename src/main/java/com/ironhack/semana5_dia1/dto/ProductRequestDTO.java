package com.ironhack.semana5_dia1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Data Transfer Object - objeto para transferir información de fuera a dentro de forma protegida/controlada
public class ProductRequestDTO {

    // obligatoria (no puede ser null) y no puede ser blank ("")
    @NotBlank(message = "Product name cannot be null or an empty or blank string")
    private String name;

    // obligatoria (no puede ser null) pero puede ser una String vacía
    @NotNull(message = "Product category cannot be null")
    private String category;

    @Min(value = 1, message = "Product price must be greater than 0")
    private int price;

    public ProductRequestDTO() {
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
}
