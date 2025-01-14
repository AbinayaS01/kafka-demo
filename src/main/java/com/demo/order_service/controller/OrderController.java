package com.demo.order_service.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.order_service.shareddto.OrderDTO;
import com.demo.order_service.shareddto.PaymentDTO;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final WebClient webClient;

    @Autowired
    public OrderController(KafkaTemplate<String, String> kafkaTemplate, WebClient.Builder webClientBuilder) {
        this.kafkaTemplate = kafkaTemplate;
        this.webClient = webClientBuilder.baseUrl("http://payment-service").build();  // Pointing to Payment Service
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO order) {
        // Send the order to Kafka topic
        kafkaTemplate.send("order_topic", order.toString());

        // Make a WebClient call to the Payment Service to process the payment
        Mono<PaymentDTO> paymentResponse = webClient.post()
                .uri("/payments")
                .bodyValue(order.getPaymentDetails())  // Sending payment details to Payment Service
                .retrieve()
                .bodyToMono(PaymentDTO.class);

        PaymentDTO payment = paymentResponse.block();  // Block here for simplicity, async could also be done

        // Log the payment info for the order
        System.out.println("Payment processed for order " + order.getOrderId() + ": " + payment);

        // Return response indicating that the order was created successfully
        return ResponseEntity.ok("Order created successfully, payment processed for order " + order.getOrderId());
    }
}