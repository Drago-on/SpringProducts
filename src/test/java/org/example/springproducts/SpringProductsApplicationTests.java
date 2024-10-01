package org.example.springproducts;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class SpringProductsApplicationTest {

    @Mock
    private ProductService productService;

    @Mock
    private ApplicationContext applicationContext;

    @InjectMocks
    private SpringProductsApplication springProductsApplication;

    @Test
    void testCommandLineRunner() throws Exception {
        try (var _ = MockitoAnnotations.openMocks(this)) {
            Product product1 = new Product(1L, "Apple", 1.99);
            Product product2 = new Product(2L, "Banana", 0.99);
            List<Product> productsNotStartingWithM = Arrays.asList(product1, product2);

            when(applicationContext.getBean(ProductService.class)).thenReturn(productService);
            when(productService.findProductsNotStartingWithM()).thenReturn(productsNotStartingWithM);

            CommandLineRunner runner = springProductsApplication.commandLineRunner(applicationContext);

            runner.run();

            verify(productService).findProductsNotStartingWithM();
            verify(applicationContext).getBean(ProductService.class);
        }
    }
}