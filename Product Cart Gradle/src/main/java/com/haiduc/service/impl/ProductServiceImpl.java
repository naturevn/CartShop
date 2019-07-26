package com.haiduc.service.impl;

import com.haiduc.model.Product;
import com.haiduc.repository.ProductRepository;
import com.haiduc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;




public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository; // CẨN THẬN CẢ NHẦM PHẦN NÀY

    @Override
    public Page<Product> findAll(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(),2);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }
}
