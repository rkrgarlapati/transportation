package com.transport.transportation.repository;

import com.transport.transportation.entity.Driver;
import com.transport.transportation.entity.TransitSource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface DriverRepository extends CrudRepository<Driver, String> {

    @Modifying
    @Query("update Driver u set u.status = ?1  where u.email = ?2")
    @Transactional
    int updateStatus(String status, String email);
}
