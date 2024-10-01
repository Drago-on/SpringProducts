package org.example.springproducts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductGettersAndSetters() {
        Product product = new Product();

        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(9.99);

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(9.99, product.getPrice());
    }

    @Test
    void testProductConstructor() {
        Product product = new Product(2L, "Another Product", 19.99);
        assertEquals(2L, product.getId());
        assertEquals("Another Product", product.getName());
        assertEquals(19.99, product.getPrice());
    }

    @Test
    void testToString() {
        Product product = new Product(3L, "Sample Product", 29.99);

        String expected = "Product{id=3, name='Sample Product', price=29.99}";

        assertEquals(expected, product.toString());
    }
}
