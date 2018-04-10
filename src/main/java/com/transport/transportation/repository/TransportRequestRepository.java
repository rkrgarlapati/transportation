package com.transport.transportation.repository;

import com.transport.transportation.entity.TransportRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface TransportRequestRepository extends CrudRepository<TransportRequest, Integer> {

    @Modifying
    @Transactional
    @Query("update TransportRequest t set t.requestStatus = ?1  where t.requestId = ?2")
    int changeRequestStatus(String requeststatus, Integer requestid);


    Optional<TransportRequest> findAllByUserType(String usertype);

}
