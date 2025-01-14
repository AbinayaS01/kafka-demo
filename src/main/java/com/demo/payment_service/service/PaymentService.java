package com.demo.payment_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.demo.payment_service.entity.Payment;

import org.springframework.kafka.annotation.EnableKafka;

@Service
@EnableKafka
public class PaymentService {

    // This listener will consume messages from the Kafka topic "order_topic"
    @KafkaListener(topics = "order_topic", groupId = "payment_group")
    public void processPayment(String orderMessage) {
        // Assuming orderMessage is in JSON format or a String, you can process it here
        // For simplicity, let's just print it for now
        System.out.println("Received order message: " + orderMessage);
        
        // Example payment object - you could parse the orderMessage into a payment object
        Payment payment = new Payment();
        payment.setPaymentId(1);
        payment.setPaymentType("card");
        System.out.println("Processed payment: " + payment);
    }
}
