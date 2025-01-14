package com.demo.order_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.order_service.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

}
