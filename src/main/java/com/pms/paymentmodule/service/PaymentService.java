package com.pms.paymentmodule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.pms.paymentmodule.model.Payment;
import com.pms.paymentmodule.repository.PaymentRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment savePayment(String transactionId, String transactionType, String bookingId, double amount, String status) {
        Payment payment = new Payment();
        payment.setTransactionId(transactionId);
        payment.setTransactionDate(new Date());
        payment.setTransactionType(transactionType);
        payment.setBookingId(bookingId);
        payment.setTransactionAmount(amount);
        payment.setTransactionStatus(status);
        
        return paymentRepository.save(payment);  // Save in DB
    }

    public Payment getTransactionById(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }
}

