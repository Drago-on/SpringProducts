package org.example.springproducts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testFindAll() {
        List<Product> products = productRepository.findAll();

        assertNotNull(products);
        assertEquals(4, products.size(), "Repository should contain 4 products initially");
    }

    @Test
    void testFindByIdExisting() {
        Product product = productRepository.findById(1L);

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("M", product.getName());
        assertEquals(700, product.getPrice());
    }

    @Test
    void testFindByIdNonExisting() {
        Product product = productRepository.findById(999L);

        assertNull(product, "Product with non-existing ID should return null");
    }

    @Test
    void testSave() {
        Product newProduct = new Product(5L, "New Product", 500);

        productRepository.save(newProduct);
        Product savedProduct = productRepository.findById(5L);

        assertNotNull(savedProduct);
        assertEquals(5L, savedProduct.getId());
        assertEquals("New Product", savedProduct.getName());
        assertEquals(500, savedProduct.getPrice());
        assertEquals(5, productRepository.findAll().size(), "Repository should now contain 5 products");
    }
}