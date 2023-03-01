package com.gms.controller;

import com.gms.jpa.entity.Product;
import com.gms.service.GroceryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("products")
public class GroceryController {

    private final GroceryService service;

    public GroceryController(GroceryService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }


    @GetMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public Product getProductByType(@RequestParam String type) {
        return service.getProductByType(type);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addProduct(@RequestBody Product input) {
        return service.addProduct(input);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product changePrice(@RequestParam Long id, @RequestParam BigDecimal newPrice) {
        return service.changePrice(id, newPrice);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteProductById(@RequestParam Long id) {
        return service.deleteProductById(id);
    }
}


