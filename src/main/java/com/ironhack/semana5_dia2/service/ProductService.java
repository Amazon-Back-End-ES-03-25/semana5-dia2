package com.ironhack.semana5_dia2.service;

import com.ironhack.semana5_dia2.dto.ProductRequestDTO;
import com.ironhack.semana5_dia2.dto.ProductResponseDTO;
import com.ironhack.semana5_dia2.dto.ProductUpdatePriceRequestDTO;
import com.ironhack.semana5_dia2.dto.ProductUpdateRequestDTO;
import com.ironhack.semana5_dia2.exception.ProductNotFoundException;
import com.ironhack.semana5_dia2.model.Product;
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
        throw new ProductNotFoundException(id);
        //return null;
    }

    public List<Product> getProductsByParams(List<String> categories, int minPrice, int maxPrice) {
        List<Product> result = new ArrayList<>();

        for (Product product : productArrayList) {

            boolean equalsCategory = categories == null || categories.isEmpty() || categories.contains(product.getCategory());

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


    public ProductResponseDTO createProductFromProductRequestDTO(ProductRequestDTO productRequestDTO) {
        Product newProduct = new Product(productArrayList.size(), productRequestDTO.getName(), productRequestDTO.getCategory(),
                productRequestDTO.getPrice());

        productArrayList.add(newProduct);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(newProduct.getId(), newProduct.getName(), newProduct.getCategory(),
                newProduct.getPrice(), "Hi, this is a new product");
        return productResponseDTO;
    }

//    public void deleteProduct(Long id){
//        productArrayList.removeIf(product -> product.getId().equals(id));
//    }


    public void deleteProduct(Long id) {
        boolean hasRemovedProduct = false;
        for (int i = 0; i < productArrayList.size(); i++) {
            Product product = productArrayList.get(i);
            if (product.getId().equals(id)) {
                productArrayList.remove(i);
                hasRemovedProduct = true;
                break;
            }
        }

        // si cuando ha terminado el bucle hasRemovedProduct sigue siendo falso es porque no existe un product con ese id
        if (!hasRemovedProduct) throw new ProductNotFoundException(id);
    }

    // Actualización completa -- PUT
    public Product updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        // Primero encuentro el producto que quiero actualizar
        Product productFromArrayList = getProductById(id); // -> me devuelve el producto encontrado o null

        if (productFromArrayList == null) {
            return null;
        }

        productFromArrayList.setName(productRequestDTO.getName());
        productFromArrayList.setCategory(productRequestDTO.getCategory());
        productFromArrayList.setPrice(productRequestDTO.getPrice());


        return productFromArrayList;
    }

    // Actualización parcial -- PATCH
    public Product partialUpdateProduct(Long id, ProductUpdateRequestDTO productUpdateRequestDTO) {
        // Primero encuentro el producto que quiero actualizar
        Product productFromArrayList = getProductById(id);

        if (productFromArrayList == null) return null;

        if (productUpdateRequestDTO.getName() != null) {
            productFromArrayList.setName(productUpdateRequestDTO.getName());
        }

        if (productUpdateRequestDTO.getCategory() != null) {
            productFromArrayList.setCategory(productUpdateRequestDTO.getCategory());
        }

        if (productUpdateRequestDTO.getPrice() != null) {
            productFromArrayList.setPrice(productUpdateRequestDTO.getPrice());
        }

        return productFromArrayList;

    }

    // Actualización parcial del Product -- actualizar el price
    public Product updateProductPrice(Long id, ProductUpdatePriceRequestDTO dto) {
        Product productFromArrayList = getProductById(id);

        if (productFromArrayList == null) return null;

        if (dto.getPrice() != null) productFromArrayList.setPrice(dto.getPrice());

        return productFromArrayList;
    }
}