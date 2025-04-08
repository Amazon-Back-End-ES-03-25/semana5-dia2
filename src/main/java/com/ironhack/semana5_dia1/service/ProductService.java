package com.ironhack.semana5_dia1.service;

import com.ironhack.semana5_dia1.dto.ProductDTO;
import com.ironhack.semana5_dia1.dto.ProductRequestDTO;
import com.ironhack.semana5_dia1.dto.ProductResponseDTO;
import com.ironhack.semana5_dia1.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ArrayList<Product> productArrayList = new ArrayList<>();

    public ProductService() {
        productArrayList.add(new Product(productArrayList.size(), "Laptop", "electronics", 80));
        productArrayList.add(new Product(productArrayList.size(), "Headphones", "electronics", 20));
        productArrayList.add(new Product(productArrayList.size(), "Notebook", "stationary", 5));
        productArrayList.add(new Product(productArrayList.size(), "Pen", "stationary", 2));
        productArrayList.add(new Product(productArrayList.size(), "Phone", "electronics", 60));
        productArrayList.add(new Product(productArrayList.size(), "Backpack", "accessories", 30));
    }

    public List<Product> getAllProducts() {
        return productArrayList;
    }

    public Product getProductById(Long id) {
        for (Product product : productArrayList) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        // throw new NoSuchElementException("Product not found with id: " + id); --> tiro una excepción si no encuentro nada
        return null;
    }

    public List<Product> getProductsByParams(List<String> categories, int minPrice, int maxPrice) {
        List<Product> result = new ArrayList<>();

        for (Product product : productArrayList) {
            // Checkear si es la misma categoria
            boolean equalsCategory = categories == null || categories.isEmpty() || categories.contains(product.getCategory());
            // Checkear si el precio está en el rango
            boolean isInMinPriceRange = product.getPrice() >= minPrice;
            boolean isInMaxPriceRange = product.getPrice() <= maxPrice;
            boolean matchesPriceRange = isInMinPriceRange && isInMaxPriceRange;

            if (equalsCategory && matchesPriceRange) {
                result.add(product);
            }
        }

        return result;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> result = new ArrayList<>();

        for (Product product : productArrayList) {
            boolean equalsCategory = category == null || product.getCategory().equalsIgnoreCase(category); //--> cuando era solo una String, no una lista
            if (equalsCategory) {
                result.add(product);
            }
        }

        return result;
    }

    public List<Product> getProductsByPriceRange(int minPrice, int maxPrice) {
        List<Product> result = new ArrayList<>();

        for (Product product : productArrayList) {
            // Checkear si el precio está en el rango
            boolean isInMinPriceRange = product.getPrice() >= minPrice;
            boolean isInMaxPriceRange = product.getPrice() <= maxPrice;
            boolean matchesPriceRange = isInMinPriceRange && isInMaxPriceRange;

            if (matchesPriceRange) {
                result.add(product);
            }
        }

        return result;
    }

    // método para crear productos que devuelve el nuevo producto creado
    public Product createProductWithoutDTO(Product product) {
        // como el usuario no sabe el size de mi arrayList le seteo el id yo
        product.setId(productArrayList.size());
        productArrayList.add(product);
        return product;
    }

    // método para crear productos que devuelve el nuevo producto creado
    public Product createProduct(Product product) {
        // como el usuario no sabe el size de mi arrayList le seteo el id yo
        product.setId(productArrayList.size());
        productArrayList.add(product);
        return product;
    }

    // recibe DTO y devuelve Product (no lo llamamos en el controller, por no poner muchos)
    public Product createProductFromDTO(ProductRequestDTO productRequestDTO) {
        // traducción de DTO (cajita controlada contenedora de datos) a mi modelo Product
        Product newProduct = new Product(productArrayList.size(), productRequestDTO.getName(), productRequestDTO.getCategory(), productRequestDTO.getPrice());
        productArrayList.add(newProduct);
        return newProduct;
    }

    // recibe request DTO y devuelve response DTO
    public ProductResponseDTO createProductFromProductRequestDTO(ProductRequestDTO productRequestDTO) {
//        if (productRequestDTO.getName() == null ||productRequestDTO.getName() == "" ){
//            throw new BadRequestException("The name is mandatory");
//        } ---> no lo tengo que hacer porque tengo validaciones

        // traducción de DTO (cajita controlada contenedora de datos) a mi modelo Product
        Product newProduct = new Product(productArrayList.size(), productRequestDTO.getName(), productRequestDTO.getCategory(),
                productRequestDTO.getPrice());

        // lo guardo en memoria (mi arrayList)
        productArrayList.add(newProduct);

        // traducción de product a mi DTO de respuesta
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(newProduct.getId(), newProduct.getName(), newProduct.getCategory(),
                newProduct.getPrice(), "Hi, this is a new product");
        return productResponseDTO;
    }

    public ProductDTO createProductByProductDTO(ProductDTO productDTO) {
        Product newProduct = new Product(productArrayList.size(), productDTO.getName(), productDTO.getCategory(), productDTO.getPrice());

        System.out.println("The created product " + newProduct);
        productArrayList.add(newProduct);

        // si no hago nada interno con este product lo devuelvo tal cual
        return productDTO;
    }
}
