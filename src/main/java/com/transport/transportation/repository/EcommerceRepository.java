package com.transport.transportation.repository;

import com.transport.transportation.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EcommerceRepository extends CrudRepository<Ecommerce, Integer> {

    @Query("select productid,discription,productname, price, imageurl from Ecommerce u")
    List<Object[]> findAllRequests();
}
