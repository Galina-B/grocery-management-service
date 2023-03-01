package com.gms.jpa.repository;

import com.gms.jpa.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
    }

    @Test
    void saveProduct() {
        Product product = createProduct();
        when(productRepository.save(any())).thenReturn(product);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertThat(product).isEqualTo(savedProduct);
    }

    @Test
    void getProductByType() {
        Product product = createProduct();
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findByType(any())).thenReturn(productList);
        List<Product> savedProductList = productRepository.findByType(product.getType());

        assertThat(productList).isEqualTo(savedProductList);
    }

    public static Product createProduct() {
        return new Product(123456L, "apple", new BigDecimal("4.55"), 2);
    }
}