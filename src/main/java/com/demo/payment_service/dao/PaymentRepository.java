package com.demo.payment_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.payment_service.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}