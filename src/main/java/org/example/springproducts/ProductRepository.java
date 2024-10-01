package org.example.springproducts;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product(1L, "M", 700));
        products.add(new Product(2L, "GL", 700));
        products.add(new Product(3L, "PM", 700));
        products.add(new Product(3L, "MF", 700));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Product product) {
        products.add(product);
    }
}