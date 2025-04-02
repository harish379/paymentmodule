package com.pms.paymentmodule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uniqueId;

    @Column(nullable = false)
    private String paymentId; // Auto-generated primary key

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String transactionId; // Unique transaction reference

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate; // Timestamp of transaction

    @Column(nullable = false)
    private String transactionType; // Credit/Debit

    @Column(nullable = false)
    private double transactionAmount; // Amount of transaction

    @Column(nullable = false)
    private String transactionStatus;// Success/Failure/Pending

    private String bookingId; // Related booking reference

    @PrePersist
	    public void generateIds() {
	        if (this.paymentId == null) {
	            this.paymentId = generatePaymentId();
	        }
	        if (this.transactionId == null) {
	            this.transactionId = generateTransactionId();
	        }

	    }

	    private String generatePaymentId() {
	        return "PAY-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
	    }

	    private String generateTransactionId() {
	        return "TXN-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
	    }

        public String getBookingId() {
            return bookingId;
        }

}
