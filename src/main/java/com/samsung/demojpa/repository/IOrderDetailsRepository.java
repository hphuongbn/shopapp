package com.samsung.demojpa.repository;

import com.samsung.demojpa.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}