package com.pms.paymentmodule.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.paymentmodule.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
