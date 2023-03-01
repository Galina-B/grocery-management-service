package com.gms.jpa.repository;

import com.gms.jpa.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Product> findByType(String type) {
        Query query = entityManager.createNativeQuery("SELECT p.* FROM product as p " +
                "WHERE p.type = ?", Product.class);
        query.setParameter(1, type);

        return query.getResultList();
    }
}
