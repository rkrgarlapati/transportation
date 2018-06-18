package com.transport.transportation.services;

import com.transport.transportation.common.CommonUtil;
import com.transport.transportation.dto.RideHistory;
import com.transport.transportation.entity.*;
import com.transport.transportation.repository.EcomTaxiRequestRepository;
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
    private EcomTaxiRequestRepository ecomTaxiRequestRepository;

    @Autowired
    private CommonUtil commonUtil;

    @GetMapping("/user/{emailid}")
    public ResponseEntity<?> getUserRides(@PathVariable String emailid) {

        SignUp user = new SignUp();
        user.setEmail(emailid);

        Iterable<TransportRequest> allrequests = transReqRepository.findAllByUser(user);
        Iterable<TransitRequest> allTransitrequests = transitRepository.findAllByUser(user);
        Iterable<EcommerceTaxiRequest> ecommerceTaxiRequests = ecomTaxiRequestRepository.findAllByUser(user);

        List<RideHistory> allReq = setValues(allrequests, allTransitrequests, ecommerceTaxiRequests);

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @GetMapping("/user/{emailid}/{reqstatus}")
    public ResponseEntity<?> getUserRidesOnStatus(@PathVariable String emailid,
                                                  @PathVariable String reqstatus) {

        SignUp user = new SignUp();
        user.setEmail(emailid);

        Iterable<TransportRequest> allrequests =
                transReqRepository.findAllByUserAndRequestStatus(user, reqstatus);

        Iterable<TransitRequest> allTransitrequests =
                transitRepository.findAllByUserAndRequestStatus(user, reqstatus);

        Iterable<EcommerceTaxiRequest> ecommerceTaxiRequests =
                ecomTaxiRequestRepository.findAllByUserAndRequestStatus(user, reqstatus);

        List<RideHistory> allReq = setValues(allrequests, allTransitrequests, ecommerceTaxiRequests);

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @GetMapping("/user/rides/{reqstatus}")
    public ResponseEntity<?> getAllRidesOnStatus(@PathVariable String reqstatus) {

        Iterable<TransportRequest> allrequests =
                transReqRepository.findAllByRequestStatus(reqstatus);

        Iterable<TransitRequest> allTransitrequests =
                transitRepository.findAllByRequestStatus(reqstatus);

        Iterable<EcommerceTaxiRequest> ecommerceTaxiRequests =
                ecomTaxiRequestRepository.findAllByRequestStatus(reqstatus);

        List<RideHistory> allReq = setValues(allrequests, allTransitrequests, ecommerceTaxiRequests);

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }


    @GetMapping("/driver/{emailid}")
    public ResponseEntity<?> getDriverRides(@PathVariable String emailid) {

        Iterable<TransportRequest> allrequests =
                transReqRepository.findAllByDriveremail(emailid);

        Iterable<TransitRequest> allTransitrequests =
                transitRepository.findAllByDriveremail(emailid);

        Iterable<EcommerceTaxiRequest> ecommerceTaxiRequests =
                ecomTaxiRequestRepository.findAllByDriveremail(emailid);


        List<RideHistory> allReq = setValues(allrequests, allTransitrequests, ecommerceTaxiRequests);

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @GetMapping("/driver/{emailid}/{reqstatus}")
    public ResponseEntity<?> getDriverRidesOnStatus(@PathVariable String emailid,
                                                    @PathVariable String reqstatus) {

        Iterable<TransportRequest> allReqs =
                transReqRepository.findAllByDriveremailAndRequestStatus(emailid, reqstatus);

        Iterable<TransitRequest> allTransitrequests =
                transitRepository.findAllByDriveremailAndRequestStatus(emailid, reqstatus);

        Iterable<EcommerceTaxiRequest> ecommerceTaxiRequests =
                ecomTaxiRequestRepository.findAllByDriveremailAndRequestStatus(emailid, reqstatus);

        List<RideHistory> allReq = setValues(allReqs, allTransitrequests, ecommerceTaxiRequests);

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    private List<RideHistory> setValues(Iterable<TransportRequest> allReqs, Iterable<TransitRequest> allTransitrequests,
                                        Iterable<EcommerceTaxiRequest> ecommerceTaxiRequests) {

        List<RideHistory> allReq = new ArrayList<>();

        allReqs.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req, "taxi");
            allReq.add(dest);
        });

        allTransitrequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req, "transit");
            allReq.add(dest);
        });

        ecommerceTaxiRequests.forEach(req -> {
            RideHistory dest = commonUtil.getCustomerHistory(req, "ecommerce");
            allReq.add(dest);
        });

        return allReq;
    }
}
