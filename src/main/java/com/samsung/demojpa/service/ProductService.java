package com.samsung.demojpa.service;

import com.samsung.demojpa.entity.Products;
import com.samsung.demojpa.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductsRepository productRepository;

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Products> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCase(query);
    }
}
