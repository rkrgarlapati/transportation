package com.transport.transportation.services;

import com.transport.transportation.entity.SignUp;
import com.transport.transportation.entity.TransportRequest;
import com.transport.transportation.repository.TaxiRequestRepository;
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

    @GetMapping("/user/{emailid}")
    public ResponseEntity<?> getUserRides(@PathVariable String emailid) {

        List<TransportRequest> transportRequests = new ArrayList<>();
        HttpStatus status;

        SignUp user = new SignUp();
        user.setEmail(emailid);
        Iterable<TransportRequest> allReqs = transReqRepository.findAllByUser(user);
        allReqs.forEach(transportRequests::add);

        if (transportRequests.size() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(transportRequests, status);
    }

    @GetMapping("/user/{emailid}/{reqstatus}")
    public ResponseEntity<?> getUserRidesOnStatus(@PathVariable String emailid,
                                                  @PathVariable String reqstatus) {
        List<TransportRequest> transportRequests = new ArrayList<>();
        HttpStatus status;

        SignUp user = new SignUp();
        user.setEmail(emailid);

        Iterable<TransportRequest> allReqs =
                transReqRepository.findAllByUserAndRequestStatus(user, reqstatus);
        allReqs.forEach(transportRequests::add);

        if (transportRequests.size() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(transportRequests, status);
    }

    @GetMapping("/driver/{emailid}")
    public ResponseEntity<?> getDriverRides(@PathVariable String emailid) {
        List<TransportRequest> transportRequests = new ArrayList<>();
        HttpStatus status;

        Iterable<TransportRequest> allReqs =
                transReqRepository.findAllByDriveremail(emailid);
        allReqs.forEach(transportRequests::add);

        if (transportRequests.size() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(transportRequests, status);
    }

    @GetMapping("/driver/{emailid}/{status}")
    public ResponseEntity<?> getDriverRidesOnStatus(@PathVariable String emailid,
                                                    @PathVariable String reqstatus) {
        List<TransportRequest> transportRequests = new ArrayList<>();
        HttpStatus status;

        Iterable<TransportRequest> allReqs =
                transReqRepository.findAllByDriveremailAndRequestStatus(emailid, reqstatus);
        allReqs.forEach(transportRequests::add);

        if (transportRequests.size() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(transportRequests, status);
    }
}
