package com.gms.jpa.repository;

import com.gms.jpa.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByType(String type);
}
