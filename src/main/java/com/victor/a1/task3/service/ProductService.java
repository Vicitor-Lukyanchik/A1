package com.victor.a1.task3.service;

import com.victor.a1.task3.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> createAll(List<Product> products);

    List<Product> findAll();
}
