package com.transport.transportation.repository;

import com.transport.transportation.entity.SignUp;
import com.transport.transportation.entity.TransitRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface TransitRepository extends CrudRepository<TransitRequest, Integer> {

    @Modifying
    @Transactional
    @Query("update TransitRequest t set t.requestStatus = ?1  where t.requestId = ?2")
    int changeRequestStatus(String requeststatus, Integer requestid);

    @Modifying
    @Transactional
    @Query("update TransitRequest t set t.warehousecharges = ?1 where t.requestId = ?2")
    int changeWarehousecharges(Double warehousecharges, Integer requestid);

    @Modifying
    @Transactional
    @Query("update TransitRequest t set t.requestStatus = ?1, t.driveremail = ?2, t.otp = ?3 where t.requestId = ?4")
    int changeRequestStatusAssignDriverOtp(String requeststatus, String driveremail, String otp, Integer requestid);

    Iterable<TransitRequest> findAllByRequestStatus(String requestStatus);
    Iterable<TransitRequest> findAllByRequestStatusNotIn(List<String> requeststatus);

    Iterable<TransitRequest> findAllByUser(SignUp email);

    Iterable<TransitRequest> findAllByUserAndRequestStatus(SignUp email, String requeststatus);

    Iterable<TransitRequest> findAllByDriveremail(String driveremail);

    Iterable<TransitRequest> findAllByDriveremailAndRequestStatus(String driveremail, String requeststatus);
}
