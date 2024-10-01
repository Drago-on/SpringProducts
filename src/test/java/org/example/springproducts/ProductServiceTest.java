package org.example.springproducts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {}

    @Test
    void testGetAllProducts() throws Exception {
        try (var _ = MockitoAnnotations.openMocks(this)) {
            Product product1 = new Product(1L, "Apple", 1.99);
            Product product2 = new Product(2L, "Banana", 0.99);
            when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

            List<Product> products = productService.getAllProducts();

            assertNotNull(products);
            assertEquals(2, products.size());
            verify(productRepository, times(1)).findAll();
        }
    }

    @Test
    void testGetProductById() throws Exception {
        try (var _ = MockitoAnnotations.openMocks(this)) {
            Product product = new Product(1L, "Apple", 1.99);
            when(productRepository.findById(1L)).thenReturn(product);

            Product result = productService.getProductById(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("Apple", result.getName());
            verify(productRepository, times(1)).findById(1L);
        }
    }

    @Test
    void testGetProductByRegex() throws Exception {
        try (var _ = MockitoAnnotations.openMocks(this)) {
            Product product1 = new Product(1L, "Apple", 1.99);
            Product product2 = new Product(2L, "Banana", 0.99);
            when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

            List<Product> result = productService.getProductByRegex("^A.*");

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Apple", result.getFirst().getName());
            verify(productRepository, times(1)).findAll();
        }
    }

    @Test
    void testFindProductsNotStartingWithM() throws Exception{
        try (var _ = MockitoAnnotations.openMocks(this)) {
            Product product1 = new Product(1L, "Apple", 1.99);
            Product product2 = new Product(2L, "Mango", 2.99);
            Product product3 = new Product(3L, "Banana", 0.99);
            when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2, product3));

            List<Product> result = productService.findProductsNotStartingWithM();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.stream().noneMatch(product -> product.getName().startsWith("M")));
            verify(productRepository, times(1)).findAll();
        }
    }

    @Test
    void testAddProduct() throws Exception{
        try (var _ = MockitoAnnotations.openMocks(this)) {
            Product product = new Product(1L, "Orange", 1.50);
            productService.addProduct(product);
            verify(productRepository, times(1)).save(product);
        }
    }
}