package com.transport.transportation.repository;

import com.transport.transportation.entity.TransitRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface TransitRepository extends CrudRepository<TransitRequest, Integer> {

    @Modifying
    @Transactional
    @Query("update TransitRequest t set t.requestStatus = ?1  where t.requestId = ?2")
    int changeRequestStatus(String requeststatus, Integer requestid);

    Optional<TransitRequest> findAllByUserType(String usertype);
}
