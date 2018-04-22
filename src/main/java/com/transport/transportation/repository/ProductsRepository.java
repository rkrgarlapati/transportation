package com.transport.transportation.repository;

import com.transport.transportation.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Integer> {
}
