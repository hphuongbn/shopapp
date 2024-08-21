package com.samsung.demojpa.repository;

import com.samsung.demojpa.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findByNameContainingIgnoreCase(String name);
}
