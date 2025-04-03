package com.pms.paymentmodule.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;


@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID bookingId;
    private Long paymentId;
    private String transactionId;
    private String invoiceNumber;

    private String receiverName;
    private String receiverAddress;
    private String receiverPin;
    private String receiverMobile;
    private double parcelWeight;
    private String parcelContentsDescription;
    private String parcelDeliveryType;
    private String parcelPackingPreference;
    private LocalDateTime parcelPickupTime;
    private LocalDateTime parcelDropoffTime;
    private BigDecimal parcelServiceCost;
    private Date parcelPaymentTime;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UUID getBookingId() {
        return bookingId;
    }
    public void setBookingId(UUID bookingId2) {
        this.bookingId = bookingId2;
    }
    public Long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
    public double getParcelWeight() {
        return parcelWeight;
    }
    public void setParcelWeight(double parcelWeight) {
        this.parcelWeight = parcelWeight;
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
    public Date getParcelPaymentTime() {
        return parcelPaymentTime;
    }
    public void setParcelPaymentTime(Date parcelPaymentTime) {
        this.parcelPaymentTime = parcelPaymentTime;
    }

    

    // Constructors, Getters & Setters
}

