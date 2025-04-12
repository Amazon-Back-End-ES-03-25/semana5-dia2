package com.ironhack.semana5_dia1.dto;

import jakarta.validation.constraints.Min;

public class ProductUpdatePriceRequestDTO {
    @Min(value = 1, message = "Price must be greater than zero")
    private Integer price;

    public ProductUpdatePriceRequestDTO() {
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
