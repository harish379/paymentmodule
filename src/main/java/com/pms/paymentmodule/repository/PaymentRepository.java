package com.pms.paymentmodule.repository;
import com.pms.paymentmodule.model.Payment;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByTransactionId(String transactionId);
    Optional<Payment> findByBookingId(UUID bookingId);
}

