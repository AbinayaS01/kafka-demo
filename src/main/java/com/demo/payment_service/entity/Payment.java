package com.demo.payment_service.entity;


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
@NoArgsConstructor 
@Entity
@Table(name="payment_details")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_id")
	private int paymentId;
	
	
	
	@Column(name="payment_type")
	private String paymentType;

}

