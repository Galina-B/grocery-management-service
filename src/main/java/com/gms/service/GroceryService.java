package com.gms.service;

import com.gms.exception.NoSuchProductException;
import com.gms.exception.ProductExistsException;
import com.gms.jpa.entity.Product;
import com.gms.jpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GroceryService {

    private final ProductRepository productRepository;
    private final ValidationService validationService;

    public GroceryService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByType(String type) {
        List<Product> products = retrieveProductByType(type);

        if (!products.isEmpty()) {
            return products.get(0);
        } else {
            throw new NoSuchProductException("Product " + type + " doesn't exist.");
        }
    }

    private List<Product> retrieveProductByType(String type) {
        validationService.typeNotNull(type);

        return productRepository.findByType(type);
    }

    public String addProduct(Product input) {
        List<Product> products = retrieveProductByType(input.getType());
        if (!products.isEmpty()) {
            throw new ProductExistsException("Product already exist.");

        } else {
            validationService.validatePriceAndType(input);
            productRepository.save(input);
            return "Product saved.";
        }
    }

    private boolean isExistingProduct(Long input) {
        return productRepository.existsById(input);
    }

    public Product changePrice(Long id, BigDecimal newPrice) {
        validationService.priceNotNullAndPositive(newPrice);
        return productRepository.findById(id).map(
                product -> {
                    product.setPrice(newPrice);
                    return productRepository.save(product);
                }
        ).orElseThrow(() -> new NoSuchProductException("Product with id=" + id + " doesn't exist."));
    }

    public String deleteProductById(Long id) {
        if (!isExistingProduct(id)) {
            throw new NoSuchProductException("Product with id=" + id + " doesn't exist.");
        } else {
            productRepository.deleteById(id);
            return "Product deleted.";
        }
    }
}
