package com.transport.transportation.services;

import com.transport.transportation.dto.DriverBlockUnblock;
import com.transport.transportation.dto.DrivingFinish;
import com.transport.transportation.dto.DrivingStart;
import com.transport.transportation.email.TransitReqSendEmailToAdmin;
import com.transport.transportation.email.TransportReqSendEmailToAdmin;
import com.transport.transportation.entity.*;
import com.transport.transportation.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/driver")
public class DriverService {

    private DriverRepository driverRepository;

    @Autowired
    private TransitReqSendEmailToAdmin transitReqSendEmailToAdmin;

    @Autowired
    private TaxiRequestRepository transReqRepository;

    @Autowired
    private TransitRepository transitRepository;

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private TransportReqSendEmailToAdmin emailToAdmin;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Driver driver) {

        HttpStatus status;

        driver.setStatus("UNBLOCK");

        SignUp signUp = new SignUp();
        signUp.setUsertype("DRIVER");
        signUp.setEmail(driver.getEmail());
        signUp.setMobile(driver.getMobile());
        signUp.setCompanyname(driver.getCompanyname());

        ResponseEntity<SignUp> responseEntity = restTemplate.postForEntity("http://localhost:8080/user/signup", signUp, SignUp.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.CREATED) {
            //result = responseEntity.getBody();
            driverRepository.save(driver);
            status = statusCode;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }

        driverRepository.save(driver);

        return new ResponseEntity<>(status);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Driver driver) {

        driverRepository.save(driver);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<?> driverBlockUnblock(@RequestBody DriverBlockUnblock driverBlockUnblock) {

        driverRepository.updateStatus(driverBlockUnblock.getStatus(), driverBlockUnblock.getEmail());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAll() {
        List<Driver> driverList = new ArrayList<>();
        HttpStatus status;

        Iterable<Driver> all = driverRepository.findAll();
        all.forEach(driverList::add);

        if (driverList.size() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(driverList, status);
    }

    /*@DeleteMapping("/{driveremail}")
    //need to delete the entry from Signup table as well....
    public ResponseEntity<?> delete(@PathVariable String driveremail) {

        driverRepository.deleteById(driveremail);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @GetMapping("/{driveremail}")
    public ResponseEntity<?> getById(@PathVariable String driveremail) {

        Optional<Driver> driver = driverRepository.findById(driveremail);

        if (driver.isPresent()) {
            return new ResponseEntity<>(driver, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{driveremail}/{requestId}/{reqStatus}")
    @Transactional
    public ResponseEntity<?> acceptRide(@PathVariable String driveremail, @PathVariable Integer requestId,
                                        @PathVariable String reqStatus,
                                        @RequestParam(name = "type", required = false) String type) {

        if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("Transit")) {
            return driverTransit(driveremail, requestId, reqStatus);
        }

        return driverTaxi(driveremail, requestId, reqStatus);
    }

    @PostMapping("/start")
    @Transactional
    public ResponseEntity<?> startRide(@RequestBody DrivingStart drivingStart,
                                       @RequestParam(name = "type", required = false) String type) {

        HttpStatus status = HttpStatus.NO_CONTENT;

        Integer requestId = drivingStart.getRequestId();
        String reqStatus = drivingStart.getReqStatus();
        String driveremail = drivingStart.getDriveremail();
        String otpReq = drivingStart.getOtp();

        if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("Transit")) {

            Optional<TransitRequest> transitRequest = transitRepository.findById(requestId);
            TransitRequest transportReq = transitRequest.get();

            if (StringUtils.isNotEmpty(transportReq.getDriveremail()) && reqStatus.equalsIgnoreCase("START")) {
                if (StringUtils.isNotEmpty(otpReq) && transportReq.getOtp().equals(otpReq) &&
                        transportReq.getRequestStatus().equals("ACK") &&
                        transportReq.getDriveremail().equals(driveremail)) {

                    transitRepository.changeRequestStatus(reqStatus, requestId);
                } else {
                    status = HttpStatus.BAD_REQUEST;
                }
            } else {
                status = HttpStatus.NOT_FOUND;
            }

            return new ResponseEntity<>(status);

        }

        Optional<TransportRequest> transportRequest = transReqRepository.findById(requestId);
        TransportRequest transportReq = transportRequest.get();

        if (StringUtils.isNotEmpty(transportReq.getDriveremail()) && reqStatus.equalsIgnoreCase("START")) {
            if (StringUtils.isNotEmpty(otpReq) && transportReq.getOtp().equals(otpReq) &&
                    transportReq.getRequestStatus().equals("ACK") &&
                    transportReq.getDriveremail().equals(driveremail)) {

                transReqRepository.changeRequestStatus(reqStatus, requestId);
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }

    @PostMapping("/finish")
    @Transactional
    public ResponseEntity<?> finishRide(@RequestBody DrivingFinish drivingFinish,
                                        @RequestParam(name = "type", required = false) String type) {

        HttpStatus status = HttpStatus.NO_CONTENT;

        Integer requestId = drivingFinish.getRequestId();
        String reqStatus = drivingFinish.getReqStatus();
        String driveremail = drivingFinish.getDriveremail();

        if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("Transit")) {

            Optional<TransitRequest> transitRequest = transitRepository.findById(requestId);
            TransitRequest transitReq = transitRequest.get();

            if (StringUtils.isNotEmpty(transitReq.getDriveremail()) &&
                    reqStatus.equalsIgnoreCase("FINISH")) {

                if (transitReq.getRequestStatus().equals("START") &&
                        transitReq.getDriveremail().equals(driveremail)) {

                    transitRepository.changeRequestStatus(reqStatus, requestId);
                } else {
                    status = HttpStatus.BAD_REQUEST;
                }
            } else {
                status = HttpStatus.NOT_FOUND;
            }

            return new ResponseEntity<>(status);
        }

        Optional<TransportRequest> transportRequest = transReqRepository.findById(requestId);
        TransportRequest transportReq = transportRequest.get();

        if (StringUtils.isNotEmpty(transportReq.getDriveremail()) &&
                reqStatus.equalsIgnoreCase("FINISH")) {

            if (transportReq.getRequestStatus().equals("START") &&
                    transportReq.getDriveremail().equals(driveremail)) {

                transReqRepository.changeRequestStatus(reqStatus, requestId);
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }

    private ResponseEntity<?> driverTaxi(String driveremail, Integer requestId, String reqStatus) {
        HttpStatus status = HttpStatus.NO_CONTENT;

        //fetch transport request details
        Optional<TransportRequest> value = transReqRepository.findById(requestId);
        TransportRequest transportRequest = value.get();

        //check if this request is already picked by any other driver.
        if (StringUtils.isBlank(transportRequest.getDriveremail()) && transportRequest.getRequestStatus().equals("A")) {

            if (reqStatus.equalsIgnoreCase("ACK")) {

                /*Invoice invoice = new Invoice();
                invoice.setRequestid(requestId);
                invoiceRepository.save(invoice);*/

                //Fetch driver details to send mail to customer and admin
                Optional<Driver> driverVal = driverRepository.findById(driveremail);
                Driver driver = driverVal.get();

                //fetch all admin emailids to send mail
                List<SignUp> allAdminUsers = signUpRepository.findByUsertype("ADMIN");
                List<String> allAdminEmailIDs = allAdminUsers.stream().map(SignUp::getEmail).collect(Collectors.toList());

                Random random = new Random();
                String otp = String.format("%04d", random.nextInt(10000));

                //assign transport request to driver.
                int count = transReqRepository.changeRequestStatusAssignDriverOtp(reqStatus, driveremail, otp, requestId);

                if (count > 0) {
                    new Thread(() -> {
                        emailToAdmin.sendEmailToAdminsAndCustomer(allAdminEmailIDs, transportRequest, driver, otp);
                    }).start();
                }
            }
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }

    private ResponseEntity<?> driverTransit(String driveremail, Integer requestId, String reqStatus) {
        HttpStatus status = HttpStatus.NO_CONTENT;

        //fetch transport request details
        Optional<TransitRequest> value = transitRepository.findById(requestId);
        TransitRequest transitRequest = value.get();

        //check if this request is already picked by any other driver.
        if (StringUtils.isBlank(transitRequest.getDriveremail())
                && transitRequest.getRequestStatus().equals("A")
                && (reqStatus.equalsIgnoreCase("ACK"))) {

            /*Invoice invoice = new Invoice();
            invoice.setRequestid(requestId);
            invoiceRepository.save(invoice);*/

            //Fetch driver details to send mail to customer and admin
            Optional<Driver> driverVal = driverRepository.findById(driveremail);
            Driver driver = driverVal.get();

            //fetch all admin emailids to send mail
            List<SignUp> allAdminUsers = signUpRepository.findByUsertype("ADMIN");
            List<String> allAdminEmailIDs = allAdminUsers.stream().map(SignUp::getEmail).collect(Collectors.toList());

            Random random = new Random();
            String otp = String.format("%04d", random.nextInt(10000));

            //assign transit request to driver.
            int count = transitRepository.changeRequestStatusAssignDriverOtp(reqStatus, driveremail, otp, requestId);

            if (count > 0) {
                new Thread(() -> {
                    transitReqSendEmailToAdmin.sendEmailToAdminsAndCustomer(allAdminEmailIDs, transitRequest, driver, otp);
                }).start();
            }

        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }

}
