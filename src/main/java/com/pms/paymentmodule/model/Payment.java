package com.pms.paymentmodule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty("cardType")
    private String transactionType; // Credit/Debit

    @Column(nullable = false)
    @JsonProperty("paymentAmount")
    private double transactionAmount; // Amount of transaction

    @Column(nullable = false)
    private String transactionStatus;// Success/Failure/Pending

    private UUID bookingId;
    
    // Related booking reference


    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    
    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

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

        public UUID getBookingId() {
            return bookingId;
        }

}
