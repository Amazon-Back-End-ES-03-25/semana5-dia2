package com.ironhack.semana5_dia1.model;

public class Product {
    // private long id;
    private Long id;
    private String name;
    private String category;
    private int price;
    private Integer businessReference; // ejemplo de atributo de uso interno en mi aplicación

    // necesito un constructor por defecto o vacío porque Jackson (traductor de JSON) lo necesita para deserializar (traductir de JSON a Java)
    public Product() {
    }

    public Product(int size, String name, String category, int price) {
        setId(size);
        this.name = name;
        this.category = category;
        this.price = price;
        setBusinessReference(123);
    }

    public Product(Long id, String name, String category, int price, Integer businessReference) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.businessReference = businessReference;
    }

    public Long getId() {
        return id;
    }

    public void setId(int size) {
        // this.id = (long) size + 1; es lo mismo
        this.id = size + 1L;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBusinessReference() {
        return businessReference;
    }

    public void setBusinessReference(Integer businessReference) {
        this.businessReference = businessReference;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", businessReference=" + businessReference +
                '}';
    }
}
