package com.transport.transportation.repository;

import com.transport.transportation.entity.SignUp;
import com.transport.transportation.entity.TransportRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaxiRequestRepository extends CrudRepository<TransportRequest, Integer> {

    @Modifying
    @Transactional
    @Query("update TransportRequest t set t.requestStatus = ?1, t.driveremail = ?2, t.otp = ?3 where t.requestid = ?4")
    int changeRequestStatusAssignDriverOtp(String requeststatus, String driveremail, String otp, Integer requestid);

    @Modifying
    @Transactional
    @Query("update TransportRequest t set t.requestStatus = ?1 where t.requestid = ?2")
    int changeRequestStatus(String requeststatus, Integer requestid);

    /*@Modifying
    @Transactional
    @Query("update TransportRequest t set t.requestStatus = ?1 and t.otp = ?2  where t.requestid = ?3")
    int changeRequestStatusAndOtp(String requeststatus, String otp, Integer requestid);*/

    Iterable<TransportRequest> findAllByUserType(String usertype);

    Iterable<TransportRequest> findAllByUserTypeAndDateTimeBetween(String usertype, Date fromdate, Date todate);

    Iterable<TransportRequest> findAllByRequestStatus(String requeststatus);
    Iterable<TransportRequest> findAllByRequestStatusNotIn(List<String> requeststatus);

    Iterable<TransportRequest> findAllByUser(SignUp email);

    Iterable<TransportRequest> findAllByUserAndRequestStatus(SignUp email, String requeststatus);

    Iterable<TransportRequest> findAllByDriveremail(String driveremail);

    Iterable<TransportRequest> findAllByDriveremailAndRequestStatus(String driveremail, String requeststatus);

}
