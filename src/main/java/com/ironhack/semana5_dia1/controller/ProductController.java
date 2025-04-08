package com.ironhack.semana5_dia1.controller;


import com.ironhack.semana5_dia1.dto.ProductDTO;
import com.ironhack.semana5_dia1.dto.ProductRequestDTO;
import com.ironhack.semana5_dia1.dto.ProductResponseDTO;
import com.ironhack.semana5_dia1.model.Product;
import com.ironhack.semana5_dia1.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products") // Hace que todos mis endpoints empiezen con /products
public class ProductController {

    // Inyección de dependencias por constructor
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/products
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // http://localhost:8080/products/id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) { // @PathVariable --> extraer el valor/variable desde nuestra URL
        return productService.getProductById(id);
    }

    // http://localhost:8080/products/search?minPrice=int&maxPrice=int&productCategory=String
    @GetMapping("/search")
    public List<Product> getProductsByParams(@RequestParam(required = false, name = "productCategory") List<String> categories, @RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "100") int maxPrice) { // @RequestParam --> extráe parámetros desde nuestra URL
        return productService.getProductsByParams(categories, minPrice, maxPrice);
    }

    @GetMapping("/search/category")
    public List<Product> getProductsByCategory(@RequestParam(required = false, name = "productCategory") String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/search/price-range")
    public List<Product> getProductsByPriceRange(@RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "100") int maxPrice) {
        return productService.getProductsByPriceRange(minPrice, maxPrice);
    }

    // Método para guardar un producto en mi aplicación y devolverlo
    @PostMapping
    public Product createProduct(@RequestBody Product product) { // anotación para traerme el cuerpo de la petición (BODY) a mi código
        return productService.createProduct(product);
    }

    @PostMapping("/product-request-dto")
    public ProductResponseDTO createProductByProductRequestDTO(@RequestBody @Valid ProductRequestDTO product) { // @Valid --> para que "chequee" las validaciones que he puesto en mi RequestDTO
        return productService.createProductFromProductRequestDTO(product);
    }

    @PostMapping("/product-dto")
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        return productService.createProductByProductDTO(product);
    }
}
