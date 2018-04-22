package com.transport.transportation.services;

import com.transport.transportation.entity.Invoice;
import com.transport.transportation.entity.TransportRequest;
import com.transport.transportation.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;


    @GetMapping("/{username}")
    public ResponseEntity<?> getAllInvoicesByUsername(@PathVariable String username) {
        //invoiceRepository.findAllById(username);

        return new ResponseEntity<>("still in progress....", HttpStatus.OK);

    }

    @GetMapping("/{invoiceid}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Integer invoiceid) {
        Optional<Invoice> value = invoiceRepository.findById(invoiceid);

        if (value.isPresent()) {
            return new ResponseEntity<>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllInvoices() {
        Iterable<Invoice> all = invoiceRepository.findAll();

        if (all.spliterator().getExactSizeIfKnown() > 0) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
