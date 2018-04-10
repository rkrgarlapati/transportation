package com.transport.transportation.repository;

import com.transport.transportation.entity.CompanyAddress;
import org.springframework.data.repository.CrudRepository;

public interface CompanyAddressRepository extends CrudRepository<CompanyAddress, Integer> {
    CompanyAddress findByUsername(String username);
}
