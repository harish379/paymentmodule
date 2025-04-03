package com.pms.paymentmodule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pms.paymentmodule.dto.ApiResponse;
import com.pms.paymentmodule.model.Payment;
import com.pms.paymentmodule.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")  
public class PaymentController {

    private final PaymentService paymentService;

    // Endpoint to store a payment transaction
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Payment>> savePayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.savePayment(payment);

         ApiResponse<Payment> response = new ApiResponse<>(true, "Payment processed successfully", savedPayment);
        return ResponseEntity.ok(response);
    }
    

    // Endpoint to retrieve transaction by ID
    @GetMapping("/{transactionId}")
    public ResponseEntity<Payment> getTransaction(@PathVariable String transactionId) {
        Payment payment = paymentService.getTransactionById(transactionId);
        return ResponseEntity.ok(payment);
    }
}

