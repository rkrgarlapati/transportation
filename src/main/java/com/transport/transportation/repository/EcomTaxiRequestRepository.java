package com.transport.transportation.repository;

import com.transport.transportation.entity.EcommerceTaxiRequest;
import com.transport.transportation.entity.SignUp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface EcomTaxiRequestRepository extends CrudRepository<EcommerceTaxiRequest, Integer> {

    @Modifying
    @Transactional
    @Query("update EcommerceTaxiRequest t set t.requestStatus = ?1, t.driveremail = ?2, t.otp = ?3 where t.requestid = ?4")
    int changeRequestStatusAssignDriverOtp(String requeststatus, String driveremail, String otp, Integer requestid);

    @Modifying
    @Transactional
    @Query("update EcommerceTaxiRequest t set t.requestStatus = ?1 where t.requestid = ?2")
    int changeRequestStatus(String requeststatus, Integer requestid);

    /*@Modifying
    @Transactional
    @Query("update TransportRequest t set t.requestStatus = ?1 and t.otp = ?2  where t.requestid = ?3")
    int changeRequestStatusAndOtp(String requeststatus, String otp, Integer requestid);*/

    Iterable<EcommerceTaxiRequest> findAllByUserType(String usertype);

    Iterable<EcommerceTaxiRequest> findAllByUserTypeAndDateTimeBetween(String usertype, Date fromdate, Date todate);

    Iterable<EcommerceTaxiRequest> findAllByRequestStatus(String requeststatus);
    Iterable<EcommerceTaxiRequest> findAllByRequestStatusNotIn(List<String> requeststatus);

    Iterable<EcommerceTaxiRequest> findAllByUser(SignUp email);

    Iterable<EcommerceTaxiRequest> findAllByUserAndRequestStatus(SignUp email, String requeststatus);

    Iterable<EcommerceTaxiRequest> findAllByDriveremail(String driveremail);

    Iterable<EcommerceTaxiRequest> findAllByDriveremailAndRequestStatus(String driveremail, String requeststatus);

}
