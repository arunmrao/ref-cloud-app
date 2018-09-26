package com.xona.api.service;

import com.xona.api.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product save(Product product);

    Product update(UUID id, Product product);

    Product findOne(UUID id);

    List<Product> findAll();

    void delete(UUID id);

}
