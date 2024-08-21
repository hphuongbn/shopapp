package com.samsung.demojpa.repository;

import com.samsung.demojpa.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {
}