package org.example.springproducts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProductsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ProductService productService = ctx.getBean(ProductService.class);

            List<Product> productsNotStartingWithM = productService.findProductsNotStartingWithM();
            System.out.println("Products starting not with M: ");
            productsNotStartingWithM.forEach(product -> System.out.println(product.toString()));
        };
    }
}
