package com.pms.paymentmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pms.paymentmodule.model.Invoice;
import com.pms.paymentmodule.service.InvoiceService;

import java.util.UUID;

@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = "*")  
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    /**
     * Generate an invoice for a given booking ID.
     * 
     * @param bookingId The ID of the booking for which the invoice needs to be generated.
     * @return The generated invoice.
     */
    @PostMapping("/generate/{bookingId}")
    public ResponseEntity<Invoice> generateInvoice(@PathVariable UUID bookingId) {
        Invoice invoice = invoiceService.generateInvoice(bookingId);
        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

    /**
     * Get invoice details by invoice number.
     * 
     * @param invoiceNumber The unique invoice number.
     * @return The invoice details.
     */
    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoiceByNumber(@PathVariable String invoiceNumber) {
        Invoice invoice = invoiceService.getInvoiceByNumber(invoiceNumber);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    /**
     * Download invoice as PDF.
     * 
     * @param bookingId The ID of the booking for which the invoice PDF needs to be generated.
     * @return PDF file as a byte array.
     */
    @GetMapping("/download/{bookingId}")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable UUID bookingId) {
        byte[] pdfBytes = invoiceService.generateInvoicePdf(bookingId);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + bookingId + ".pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
