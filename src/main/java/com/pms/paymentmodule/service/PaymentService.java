package com.pms.paymentmodule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.paymentmodule.model.Payment;
import com.pms.paymentmodule.repository.PaymentRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private RestTemplate restTemplate;

    public Payment savePayment(Payment payment) {
        payment.setTransactionDate(new Date()); // Set transaction date before saving


        String bookingId = payment.getBookingId();
        updatePaymentTimeInBooking(bookingId);
        return paymentRepository.save(payment);  // Save in DB
    }

    public Payment getTransactionById(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }




    public void updatePaymentTimeInBooking(String bookingId) {
        try {
            String url = "http://localhost:8082/bookings/updatePaymentTime?bookingId=" + bookingId;
            restTemplate.put(url, null);
        } catch (Exception e) {
            System.err.println("Error updating booking payment time: " + e.getMessage());
        }
    }
}

