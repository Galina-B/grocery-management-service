package com.gms.service;

import com.gms.exception.NoSuchProductException;
import com.gms.exception.ProductExistsException;
import com.gms.jpa.entity.Product;
import com.gms.jpa.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gms.jpa.repository.ProductRepositoryTest.createProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroceryServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private GroceryService groceryService;

    List<Product> productList = new ArrayList<>();

    Product product;

    @BeforeEach
    void setUp() {
        product = createProduct();
        productList.add(product);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> foundProductList = groceryService.getAllProducts();

        assertThat(productList).isEqualTo(foundProductList);
    }

    @Test
    void getProductByType() {
        when(productRepository.findByType(product.getType())).thenReturn(productList);
        Product foundProduct = groceryService.getProductByType(product.getType());

        assertThat(product).isEqualTo(foundProduct);
    }

    @Test
    void getProductByTypeThrowsNoSuchProductException() {
        when(productRepository.findByType("milk")).thenReturn(new ArrayList<>());

        assertThrows(NoSuchProductException.class, () -> groceryService.getProductByType("milk"));
    }

    @Test
    void addProduct() {
        when(productRepository.save(product)).thenReturn(product);
        String expectedMessage = "Product saved.";
        String actualMessage = groceryService.addProduct(product);

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void addProductThrowsProductExistsException() {
        when(productRepository.findByType("apple")).thenReturn(productList);

        assertThrows(ProductExistsException.class, () -> groceryService.addProduct(product));
    }

    @Test
    void changePrice() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
        when(productRepository.save(product)).thenReturn(product);
        Product expected = createProduct();
        BigDecimal newPrice = BigDecimal.valueOf(7000000);
        expected.setPrice(newPrice);

        Product actual = groceryService.changePrice(product.getId(), newPrice);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void delete() {
        when(productRepository.existsById(product.getId())).thenReturn(true);
        String expectedMessage = "Product deleted.";
        String actualMessage = groceryService.deleteProductById(product.getId());

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}