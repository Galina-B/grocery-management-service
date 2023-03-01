package com.gms.config;

import com.gms.jpa.entity.Product;
import com.gms.jpa.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * Dummy data loaded into database
 */
@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {
            Product product1 = Product.builder()
                    .type("bread")
                    .quantity(10)
                    .price(new BigDecimal(5.20))
                    .build();

            Product product2 = Product.builder()
                    .type("rice")
                    .quantity(3)
                    .price(new BigDecimal(10.10))
                    .build();

            logger.info("Preloading " + repository.save(product1));
            logger.info("Preloading " + repository.save(product2));
        };
    }
}
