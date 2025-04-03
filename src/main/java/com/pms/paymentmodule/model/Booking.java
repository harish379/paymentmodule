package com.pms.paymentmodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "User ID is required")
    @Column(nullable = false)
    private UUID userId;

    @NotBlank(message = "Receiver name is required")
    @Column(nullable = false)
    private String receiverName;

    @NotBlank(message = "Receiver address is required")
    @Column(nullable = false)
    private String receiverAddress;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid receiver PIN")
    @Column(nullable = false)
    private String receiverPin;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid receiver mobile number")
    @Column(nullable = false)
    private String receiverMobile;

    @Min(value = 1, message = "Parcel weight must be at least 1 gram")
    @Column(nullable = false)
    private int parcelWeightInGram;

    @NotBlank(message = "Parcel contents description is required")
    @Column(nullable = false, length = 500)
    private String parcelContentsDescription;

    @NotBlank(message = "Parcel delivery type is required")
    @Column(nullable = false)
    private String parcelDeliveryType;

    @NotBlank(message = "Parcel packing preference is required")
    @Column(nullable = false)
    private String parcelPackingPreference;

    @NotNull(message = "Parcel pickup time is required")
    @Column(nullable = false)
    private LocalDateTime parcelPickupTime;

    @NotNull(message = "Parcel dropoff time is required")
    @Column(nullable = false)
    private LocalDateTime parcelDropoffTime;

    @DecimalMin(value = "0.0", inclusive = false, message = "Parcel service cost must be greater than 0")
    @Column(nullable = false)
    private BigDecimal parcelServiceCost;

    @Column(nullable = false, updatable = false)
    private LocalDateTime parcelPaymentTime;

    // Status field to track the booking status (default: PENDING)
    @NotBlank(message = "Booking status is required")
    @Column(nullable = false)
    @Builder.Default
    private String status = "PENDING";


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPin() {
        return receiverPin;
    }

    public void setReceiverPin(String receiverPin) {
        this.receiverPin = receiverPin;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public int getParcelWeightInGram() {
        return parcelWeightInGram;
    }

    public void setParcelWeightInGram(int parcelWeightInGram) {
        this.parcelWeightInGram = parcelWeightInGram;
    }

    public String getParcelContentsDescription() {
        return parcelContentsDescription;
    }

    public void setParcelContentsDescription(String parcelContentsDescription) {
        this.parcelContentsDescription = parcelContentsDescription;
    }

    public String getParcelDeliveryType() {
        return parcelDeliveryType;
    }

    public void setParcelDeliveryType(String parcelDeliveryType) {
        this.parcelDeliveryType = parcelDeliveryType;
    }

    public String getParcelPackingPreference() {
        return parcelPackingPreference;
    }

    public void setParcelPackingPreference(String parcelPackingPreference) {
        this.parcelPackingPreference = parcelPackingPreference;
    }

    public LocalDateTime getParcelPickupTime() {
        return parcelPickupTime;
    }

    public void setParcelPickupTime(LocalDateTime parcelPickupTime) {
        this.parcelPickupTime = parcelPickupTime;
    }

    public LocalDateTime getParcelDropoffTime() {
        return parcelDropoffTime;
    }

    public void setParcelDropoffTime(LocalDateTime parcelDropoffTime) {
        this.parcelDropoffTime = parcelDropoffTime;
    }

    public BigDecimal getParcelServiceCost() {
        return parcelServiceCost;
    }

    public void setParcelServiceCost(BigDecimal parcelServiceCost) {
        this.parcelServiceCost = parcelServiceCost;
    }

    public LocalDateTime getParcelPaymentTime() {
        return parcelPaymentTime;
    }

    public void setParcelPaymentTime(LocalDateTime parcelPaymentTime) {
        this.parcelPaymentTime = parcelPaymentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




    @PrePersist
    protected void onCreate() {
        parcelPaymentTime = LocalDateTime.now();
    }




}
