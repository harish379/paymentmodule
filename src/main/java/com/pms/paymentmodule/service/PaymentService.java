package com.pms.paymentmodule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.pms.paymentmodule.model.Payment;
import com.pms.paymentmodule.repository.PaymentRepository;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        payment.setTransactionDate(new Date()); // Set transaction date before saving
        payment.setTransactionStatus("Success"); // Set transaction status as success
        return paymentRepository.save(payment);  // Save in DB
    }

    public Payment getTransactionById(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }

    public Payment getPaymentByBookingId(String bookingId) {
        UUID uuid = UUID.fromString(bookingId); // Convert String to UUID
        return paymentRepository.findByBookingId(uuid)
                .orElseThrow(() -> new RuntimeException("Payment not found for bookingId: " + bookingId));
    }
   
}

