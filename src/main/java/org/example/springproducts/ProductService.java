package org.example.springproducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductByRegex(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return productRepository.findAll().stream()
                .filter(product -> pattern.matcher(product.getName()).matches())
                .collect(Collectors.toList());
    }

    public List<Product> findProductsNotStartingWithM() {
        return productRepository.findAll().stream()
                .filter(product -> !product.getName().startsWith("M"))
                .collect(Collectors.toList());
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
