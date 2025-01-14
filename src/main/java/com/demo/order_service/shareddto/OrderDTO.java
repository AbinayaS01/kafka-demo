package com.demo.order_service.shareddto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int orderId;
    private LocalDate orderDate;
    private double orderAmount;
    private String paymentDetails;  // Payment details for the order
}
