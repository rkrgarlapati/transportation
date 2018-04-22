package com.transport.transportation.repository;

import com.transport.transportation.entity.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServicesRepository extends CrudRepository<Service, Integer> {
}
