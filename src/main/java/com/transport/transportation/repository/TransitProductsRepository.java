package com.transport.transportation.repository;

import com.transport.transportation.entity.TransitProducts;
import org.springframework.data.repository.CrudRepository;

public interface TransitProductsRepository extends CrudRepository<TransitProducts, Integer> {
}
