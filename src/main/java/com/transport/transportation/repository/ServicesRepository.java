package com.transport.transportation.repository;

import com.transport.transportation.entity.TransitServices;
import org.springframework.data.repository.CrudRepository;

public interface ServicesRepository extends CrudRepository<TransitServices, Integer> {
}
