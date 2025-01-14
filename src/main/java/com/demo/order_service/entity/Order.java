package com.demo.order_service.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Lombok annotation to generate getters, setters, toString, equals, hashCode
@AllArgsConstructor  // Lombok annotation to generate constructor with all fields
@NoArgsConstructor   // Lombok annotation to generate default constructor
@Entity
@Table(name = "order_details")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private LocalDate orderDate;
    private double orderAmount;
    private String paymentDetails;  // This will hold the payment details received from PaymentService
}
