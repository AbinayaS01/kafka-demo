package com.demo.payment_service.controller;

import org.springframework.web.bind.annotation.*;

import com.demo.payment_service.shareddto.PaymentDTO;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping
    public PaymentDTO processPayment(@RequestBody String paymentDetails) {
        // Here you would typically have some payment logic
        System.out.println("Received payment details: " + paymentDetails);

        // Simulate payment processing by returning a dummy PaymentDTO
        PaymentDTO payment = new PaymentDTO();
        payment.setPaymentId(1);  // Dummy ID
        payment.setPaymentType("card");  // Assuming it's a card payment

        return payment;
    }
}