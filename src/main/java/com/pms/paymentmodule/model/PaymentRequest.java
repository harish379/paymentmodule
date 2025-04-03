package com.pms.paymentmodule.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String transactionId;
    private String transactionType;
    private String bookingId;
    private double transactionAmount;
    private String transactionStatus;
    private String userId;
    
}

