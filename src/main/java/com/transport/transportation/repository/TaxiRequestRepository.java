package com.transport.transportation.repository;

import com.transport.transportation.entity.TransportRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

public interface TaxiRequestRepository extends CrudRepository<TransportRequest, Integer> {

    @Modifying
    @Transactional
    @Query("update TransportRequest t set t.requestStatus = ?1  where t.requestid = ?2")
    int changeRequestStatus(String requeststatus, Integer requestid);


    Iterable<TransportRequest> findAllByUserType(String usertype);

    Iterable<TransportRequest> findAllByUserTypeAndDateTimeBetween(String usertype, Date fromdate, Date todate);

    Iterable<TransportRequest> findAllByRequestStatus(String requeststatus);
}
