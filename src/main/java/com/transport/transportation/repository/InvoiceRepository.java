package com.transport.transportation.repository;

import com.transport.transportation.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
}
