package com.pms.paymentmodule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pms.paymentmodule.model.Payment;
import com.pms.paymentmodule.model.PaymentRequest;
import com.pms.paymentmodule.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") 
public class PaymentController {

    private final PaymentService paymentService;

    // Endpoint to store a payment transaction
    @PostMapping("/save")
    public ResponseEntity<Payment> savePayment(@RequestBody PaymentRequest request) {
        Payment payment = paymentService.savePayment(
                request.getTransactionId(),
                request.getTransactionType(),
                request.getBookingId(),
                request.getTransactionAmount(),
                request.getTransactionStatus()
        );
        return ResponseEntity.ok(payment);
    }

    // Endpoint to retrieve transaction by ID
    @GetMapping("/{transactionId}")
    public ResponseEntity<Payment> getTransaction(@PathVariable String transactionId) {
        Payment payment = paymentService.getTransactionById(transactionId);
        return ResponseEntity.ok(payment);
    }
}

