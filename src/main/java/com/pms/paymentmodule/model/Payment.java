package com.pms.paymentmodule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;  // Auto-generated primary key

    @Column(nullable = false, unique = true)
    private String transactionId; // Unique transaction reference

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate; // Timestamp of transaction

    @Column(nullable = false)
    private String transactionType; // Credit/Debit

    @Column(nullable = false)
    private String bookingId; // Related booking reference

    @Column(nullable = false)
    private double transactionAmount; // Amount of transaction

    @Column(nullable = false)
    private String transactionStatus; // Success/Failure/Pending
}
