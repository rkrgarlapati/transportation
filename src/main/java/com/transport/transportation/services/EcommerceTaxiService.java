package com.transport.transportation.services;

import com.transport.transportation.common.CommonUtil;
import com.transport.transportation.csv.GenerateCSV;
import com.transport.transportation.dto.RequestIdStatus;
import com.transport.transportation.email.EcommerceReqSendEmailToAdmin;
import com.transport.transportation.entity.*;
import com.transport.transportation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ecomtaxi")
public class EcommerceTaxiService {

    @Autowired
    private EcomTaxiRequestRepository ecomTaxiRequestRepository;

    @Autowired
    private EcommerceRepository ecommerceRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private GenerateCSV csv;

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private EcommerceReqSendEmailToAdmin ecommerceEmail;

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private DriverRepository driverRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> transportRequest(@RequestBody EcommerceRequestPost ecommerceRequestPost) {

        EcommerceTaxiRequest ecomtransReq = new EcommerceTaxiRequest();

        Destination dest = new Destination();
        Source source = new Source();
        SignUp user = new SignUp();

        ecomtransReq.setRequestStatus("P");
        user.setEmail(ecommerceRequestPost.getEmail());

        Integer sourceId = ecommerceRequestPost.getSourceId();
        if (sourceId != null && sourceId > 0) {
            ecomtransReq.setSourceid(sourceId);
            source.setSourceid(sourceId);
            ecomtransReq.setSour(source);
            Optional<Source> sourceVal = sourceRepository.findById(source.getSourceid());
            source.setSourcename(sourceVal.get().getSourcename());
        }

        Integer destId = ecommerceRequestPost.getDestinationId();
        if (destId != null && destId > 0) {
            ecomtransReq.setDestinationid(destId);
            dest.setDestinationid(destId);
            ecomtransReq.setDest(dest);
            Optional<Destination> destiVal = destinationRepository.findById(dest.getDestinationid());
            dest.setDestinationname(destiVal.get().getDestinationname());
        }

        ecomtransReq.setUser(user);
        ecomtransReq.setUserType(ecommerceRequestPost.getUserType().toUpperCase());
        ecomtransReq.setCashInHand(ecommerceRequestPost.getCashInHand());
        ecomtransReq.setDateTime(ecommerceRequestPost.getDateTime());
        ecomtransReq.setMobileNo(ecommerceRequestPost.getMobileNo());
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setProductid(ecommerceRequestPost.getProductid());
        ecomtransReq.setEcommerce(ecommerce);
        ecomtransReq.setRemarks(ecommerceRequestPost.getRemarks());

        EcommerceTaxiRequest inserted = ecomTaxiRequestRepository.save(ecomtransReq);

        Optional<Ecommerce> productOpt = ecommerceRepository.findById(inserted.getEcommerce().getProductid());

        List<SignUp> allAdminUsers = signUpRepository.findByUsertype("ADMIN");
        List<String> allAdminEmailIDs = getAllEmailIDs(allAdminUsers);

        if (inserted != null) {
            new Thread(() -> {
                ecommerceEmail.sendEcommerceTaxiRequestEmails(allAdminEmailIDs, inserted, productOpt.get(), "P");
            }).start();
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{reqStatus}")
    public ResponseEntity<Iterable<EcomRequestCustom>> getAllByRequestStatus(@PathVariable String reqStatus) {

        Iterable<EcommerceTaxiRequest> allrequests = ecomTaxiRequestRepository.findAllByRequestStatus(reqStatus);

        List<EcomRequestCustom> allReq = new ArrayList<>();
        allrequests.forEach(req -> {
            EcomRequestCustom dest = commonUtil.copyEcomRequest(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> approveRequest(@RequestBody RequestIdStatus appReq) {

        HttpStatus status;

        int count = ecomTaxiRequestRepository.changeRequestStatus(appReq.getReqStatus(), appReq.getRequestId());

        if (count > 0) {
            if (appReq.getReqStatus().equalsIgnoreCase("A")) {

                Optional<EcommerceTaxiRequest> value = ecomTaxiRequestRepository.findById(appReq.getRequestId());

                EcommerceTaxiRequest transportRequest = value.get();

                List<Driver> allDrivers = driverRepository.findByStatus("UNBLOCK");
                List<String> allDriversEmailIDs = allDrivers.stream().map(Driver::getEmail).collect(Collectors.toList());

                /*new Thread(() -> {
                    emailToAdmin.sendTransportRequestEmails(allDriversEmailIDs, transportRequest, "A");
                }).start();*/

            }
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }

    @GetMapping("/request/{requestId}")
    public ResponseEntity<EcomRequestCustom> viewRequestById(@PathVariable() Integer requestId) {

        Optional<EcommerceTaxiRequest> value = ecomTaxiRequestRepository.findById(requestId);

        if (value.isPresent()) {
            EcommerceTaxiRequest transReq = value.get();
            EcomRequestCustom dest = commonUtil.copyEcomRequest(transReq);
            return new ResponseEntity<>(dest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> viewAllRequests() {

        Iterable<EcommerceTaxiRequest> allrequests = ecomTaxiRequestRepository.findAll();

        List<EcomRequestCustom> allReq = new ArrayList<>();
        allrequests.forEach(req -> {
            EcomRequestCustom dest = commonUtil.copyEcomRequest(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    private List<String> getAllEmailIDs(List<SignUp> allAdminUsers) {

        return allAdminUsers.stream().map(SignUp::getEmail).collect(Collectors.toList());
    }
}