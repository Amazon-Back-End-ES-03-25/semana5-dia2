package com.ironhack.semana5_dia2.controller;


import com.ironhack.semana5_dia2.dto.ProductRequestDTO;
import com.ironhack.semana5_dia2.dto.ProductResponseDTO;
import com.ironhack.semana5_dia2.dto.ProductUpdatePriceRequestDTO;
import com.ironhack.semana5_dia2.dto.ProductUpdateRequestDTO;
import com.ironhack.semana5_dia2.model.Product;
import com.ironhack.semana5_dia2.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) { // @PathVariable --> extraer el valor/variable desde nuestra URL
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductsByParams(@RequestParam(required = false, name = "productCategory") List<String> categories, @RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "100") int maxPrice) { // @RequestParam --> extráe parámetros desde nuestra URL
        return ResponseEntity.ok(productService.getProductsByParams(categories, minPrice, maxPrice));
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(required = false, name = "productCategory") String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/search/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "100") int maxPrice) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }

    @PostMapping("/product-request-dto")
    public ResponseEntity<ProductResponseDTO> createProductByProductRequestDTO(@RequestBody @Valid ProductRequestDTO product) { // @Valid --> para que "chequee" las validaciones que he puesto en mi RequestDTO
        ProductResponseDTO responseDTO = productService.createProductFromProductRequestDTO(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Se utilizan para hacer actualizaciones COMPLETAS
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequestDTO));
    }

    // Se utilizan para hacer actualizaciones PARCIALES
    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateRequestDTO productUpdateRequestDTO) {
        return ResponseEntity.ok(productService.partialUpdateProduct(id, productUpdateRequestDTO));
    }

    // Actualización de solo el price del Product
    @PatchMapping("/{id}/price")
    public ResponseEntity<Product> updateProductPrice(@PathVariable Long id, @RequestBody @Valid ProductUpdatePriceRequestDTO dto) {
        return ResponseEntity.ok(productService.updateProductPrice(id, dto));
    }

    // Códigos de estado HTTP
    // 200 OK -- todo ha ido bien
    // 201 Created -- algo nuevo ha sido creado
    // 204 No Content -- ha ido bien y no devuelvo nada
    // 400 Bad Request -- el usuario manda datos inválidos
    // 404 Not Found -- no se encontró lo que pides
    // 500 Server Error -- fallo interno inesperado (error no controlado en el código)
}
