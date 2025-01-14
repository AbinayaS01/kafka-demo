package com.demo.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.order_service.entity.Order;
import com.demo.order_service.shareddto.PaymentDTO;


@Service
public class OrderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final WebClient webClient;

    @Autowired
    public OrderService(KafkaTemplate<String, String> kafkaTemplate, WebClient.Builder webClientBuilder) {
        this.kafkaTemplate = kafkaTemplate;
        this.webClient = webClientBuilder.baseUrl("http://payment-service").build();  // Payment service URL
    }

    public void createOrder(Order order) {
        // Send order to Kafka topic
        kafkaTemplate.send("order_topic", order.toString());

        // Make a WebClient call to Payment Service to get payment details
        PaymentDTO payment = webClient.post()
            .uri("/payments")
            .bodyValue(order.getPaymentDetails())
            .retrieve()
            .bodyToMono(PaymentDTO.class)
            .block();  // You can replace `block()` with async handling if needed

        // Process the payment details with the received payment info
        System.out.println("Payment info for order: " + payment);
    }
}
