package com.transport.transportation.services;

import com.transport.transportation.common.CommonUtil;
import com.transport.transportation.dto.RideHistory;
import com.transport.transportation.entity.SignUp;
import com.transport.transportation.entity.TransitRequest;
import com.transport.transportation.entity.TransportRequest;
import com.transport.transportation.repository.TaxiRequestRepository;
import com.transport.transportation.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ridehistory")
public class DriverAndUserRideHistory {

    @Autowired
    private TaxiRequestRepository transReqRepository;

    @Autowired
    private TransitRepository transitRepository;

    @Autowired
    private CommonUtil commonUtil;

    @GetMapping("/user/{emailid}")
    public ResponseEntity<?> getUserRides(@PathVariable String emailid) {
        List<RideHistory> allReq = new ArrayList<>();
        SignUp user = new SignUp();
        user.setEmail(emailid);

        Iterable<TransportRequest> allrequests = transReqRepository.findAllByUser(user);
        Iterable<TransitRequest> allTransitrequests = transitRepository.findAllByUser(user);

        allrequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req);
            allReq.add(dest);
        });

        allTransitrequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @GetMapping("/user/{emailid}/{reqstatus}")
    public ResponseEntity<?> getUserRidesOnStatus(@PathVariable String emailid,
                                                  @PathVariable String reqstatus) {

        List<RideHistory> allReq = new ArrayList<>();

        SignUp user = new SignUp();
        user.setEmail(emailid);

        Iterable<TransportRequest> allrequests =
                transReqRepository.findAllByUserAndRequestStatus(user, reqstatus);

        Iterable<TransitRequest> allTransitrequests =
                transitRepository.findAllByUserAndRequestStatus(user, reqstatus);

        allrequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req);
            allReq.add(dest);
        });

        allTransitrequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @GetMapping("/driver/{emailid}")
    public ResponseEntity<?> getDriverRides(@PathVariable String emailid) {

        List<RideHistory> allReq = new ArrayList<>();

        Iterable<TransportRequest> allrequests =
                transReqRepository.findAllByDriveremail(emailid);

        Iterable<TransitRequest> allTransitrequests =
                transitRepository.findAllByDriveremail(emailid);

        allrequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req);
            allReq.add(dest);
        });

        allTransitrequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @GetMapping("/driver/{emailid}/{reqstatus}")
    public ResponseEntity<?> getDriverRidesOnStatus(@PathVariable String emailid,
                                                    @PathVariable String reqstatus) {
        List<RideHistory> allReq = new ArrayList<>();

        Iterable<TransportRequest> allReqs =
                transReqRepository.findAllByDriveremailAndRequestStatus(emailid, reqstatus);

        Iterable<TransitRequest> allTransitrequests =
                transitRepository.findAllByDriveremailAndRequestStatus(emailid, reqstatus);

        allReqs.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req);
            allReq.add(dest);
        });

        allTransitrequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }
}
