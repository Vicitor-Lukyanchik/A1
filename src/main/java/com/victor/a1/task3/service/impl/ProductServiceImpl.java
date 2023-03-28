package com.victor.a1.task3.service.impl;

import com.victor.a1.task3.entity.Product;
import com.victor.a1.task3.repository.ProductRepository;
import com.victor.a1.task3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> createAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
