package com.pms.paymentmodule.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;
import com.pms.paymentmodule.model.Booking;
import com.pms.paymentmodule.model.Invoice;
import com.pms.paymentmodule.model.Payment;
import com.pms.paymentmodule.repository.BookingRepository;
import com.pms.paymentmodule.repository.InvoiceRepository;
import com.pms.paymentmodule.repository.PaymentRepository;

import java.io.ByteArrayOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Service
public class InvoiceService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public void generateInvoice(UUID bookingId) {
        // Fetch booking details from the local database

        Optional<Invoice> existing = invoiceRepository.findByBookingId(bookingId);
        if (existing.isPresent()) {
            return; // Invoice already exists for this booking
        }
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found for ID: " + bookingId));

        // Fetch payment details from Payment Table
        Payment payment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Payment not found for Booking ID: " + bookingId));

        // Generate Invoice Number
        String invoiceNumber = "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Create and Save Invoice
        Invoice invoice = new Invoice();
        invoice.setBookingId(bookingId);
        invoice.setPaymentId(payment.getUniqueId());
        invoice.setTransactionId(payment.getTransactionId());
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setReceiverName(booking.getReceiverName());
        invoice.setReceiverAddress(booking.getReceiverAddress());
        invoice.setReceiverPin(booking.getReceiverPin());
        invoice.setReceiverMobile(booking.getReceiverMobile());
        invoice.setParcelWeight(booking.getParcelWeightInGram());
        invoice.setParcelContentsDescription(booking.getParcelContentsDescription());
        invoice.setParcelDeliveryType(booking.getParcelDeliveryType());
        invoice.setParcelPackingPreference(booking.getParcelPackingPreference());
        invoice.setParcelPickupTime(booking.getParcelPickupTime());
        invoice.setParcelDropoffTime(booking.getParcelDropoffTime());
        invoice.setParcelServiceCost(booking.getParcelServiceCost());
        invoice.setParcelPaymentTime(payment.getTransactionDate());

         invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceByNumber(String invoiceNumber) {
        return invoiceRepository.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new RuntimeException("Invoice not found for number: " + invoiceNumber));
    }

    public byte[] generateInvoicePdf(UUID bookingId) {
        Invoice invoice = invoiceRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Invoice not found for bookingId: " + bookingId));
    
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
    
            // Company Name - ParcelFlow in Blue
            Font companyFont = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, new BaseColor(0, 0, 255));
            Paragraph companyTitle = new Paragraph("ParcelFlow", companyFont);
            companyTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(companyTitle);
            document.add(new Paragraph("\n"));
    
            // Invoice Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("INVOICE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));
    
            // Add Invoice Table
            PdfPTable table = new PdfPTable(2); // 2 Columns
            table.setWidthPercentage(100); // Full Width
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);
            table.setWidths(new float[]{1.5f, 3.5f});
            document.add(new Paragraph("\n")); // Adjust column width ratios
    
            // Invoice Details Section
            table.addCell(createSectionHeader("Invoice Details"));
            table.addCell("Invoice Number:");
            table.addCell(invoice.getInvoiceNumber());
            table.addCell("Booking ID:");
            table.addCell(invoice.getBookingId().toString());
            table.addCell("Payment ID:");
            table.addCell(invoice.getPaymentId().toString());
            table.addCell("Transaction ID:");
            table.addCell(invoice.getTransactionId());
            table.addCell("Transaction Amount:");
            table.addCell("$" + invoice.getParcelServiceCost());
            table.addCell("Payment Time:");
            table.addCell(invoice.getParcelPaymentTime().toString());
            document.add(new Paragraph("\n"));
    
            // Receiver Details Section
            table.addCell(createSectionHeader("Receiver Details"));
            table.addCell("Receiver Name:");
            table.addCell(invoice.getReceiverName());
            table.addCell("Receiver Address:");
            table.addCell(invoice.getReceiverAddress());
            table.addCell("Receiver Pin:");
            table.addCell(invoice.getReceiverPin());
            table.addCell("Receiver Mobile:");
            table.addCell(invoice.getReceiverMobile());
            document.add(new Paragraph("\n"));
    
            // Parcel Details Section
            table.addCell(createSectionHeader("Parcel Details"));
            table.addCell("Parcel Weight:");
            table.addCell(invoice.getParcelWeight() + " grams");
            table.addCell("Contents Description:");
            table.addCell(invoice.getParcelContentsDescription());
            table.addCell("Delivery Type:");
            table.addCell(invoice.getParcelDeliveryType());
            table.addCell("Packing Preference:");
            table.addCell(invoice.getParcelPackingPreference());
            table.addCell("Service Cost:");
            table.addCell("$" + invoice.getParcelServiceCost());
            document.add(new Paragraph("\n"));
    
            document.add(table);
            document.close();
            return baos.toByteArray(); // Convert PDF to byte array
        } catch (Exception e) {
            throw new RuntimeException("Error generating invoice PDF", e);
        }
    }
    

    private PdfPCell createSectionHeader(String titleText) {
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 17, Font.BOLD, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(titleText, headerFont));
        cell.setColspan(4);
        cell.setBackgroundColor(new BaseColor(0, 1, 116)); // Dark Blue
        cell.setPadding(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
    

}

