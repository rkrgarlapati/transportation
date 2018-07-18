package com.transport.transportation.services;

import com.transport.transportation.common.CommonUtil;
import com.transport.transportation.csv.GenerateCSV;
import com.transport.transportation.dto.SendTransportDocument;
import com.transport.transportation.dto.RequestIdStatus;
import com.transport.transportation.email.TransportReqSendEmailToAdmin;
import com.transport.transportation.entity.*;
import com.transport.transportation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/taxi")
public class TaxiService {

    @Autowired
    private TaxiRequestRepository transReqRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private GenerateCSV csv;

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private TransportReqSendEmailToAdmin emailToAdmin;

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private DriverRepository driverRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> transportRequest(@RequestBody TransportRequestPost transRequest) {

        TransportRequest transReq = new TransportRequest();

        Destination dest = new Destination();
        Source source = new Source();
        SignUp user = new SignUp();

        dest.setDestinationid(transRequest.getDestinationId());
        transReq.setDest(dest);
        source.setSourceid(transRequest.getSourceId());
        transReq.setSour(source);
        transReq.setRequestStatus("P");
        user.setEmail(transRequest.getEmail());
        transReq.setSourceId(transRequest.getSourceId());
        transReq.setDestinationId(transRequest.getDestinationId());
        transReq.setUser(user);
        transReq.setUserType(transRequest.getUserType().toUpperCase());
        transReq.setRoundTrips(transRequest.getRoundTrips());
        transReq.setMultipleTrips(transRequest.getMultipleTrips());
        transReq.setCashInHand(transRequest.getCashInHand());
        transReq.setDateTime(transRequest.getDateTime());
        transReq.setMobileNo(transRequest.getMobileNo());
        transReq.setCost(transRequest.getCost());

        TransportRequest inserted = transReqRepository.save(transReq);

        Optional<Source> sourceVal = sourceRepository.findById(source.getSourceid());
        source.setSourcename(sourceVal.get().getSourcename());

        Optional<Destination> destiVal = destinationRepository.findById(dest.getDestinationid());
        dest.setDestinationname(destiVal.get().getDestinationname());

        List<SignUp> allAdminUsers = signUpRepository.findByUsertype("ADMIN");
        List<String> allAdminEmailIDs = getAllEmailIDs(allAdminUsers);

        if (inserted != null) {
            new Thread(() -> {
                emailToAdmin.sendTransportRequestEmails(allAdminEmailIDs, inserted, "P");
            }).start();
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{reqStatus}")
    public ResponseEntity<Iterable<TransRequestCustom>> getAllByRequestStatus(@PathVariable String reqStatus) {

        Iterable<TransportRequest> allrequests = transReqRepository.findAllByRequestStatus(reqStatus);

        List<TransRequestCustom> allReq = new ArrayList<>();
        allrequests.forEach(req -> {
            TransRequestCustom dest = commonUtil.copyRequest(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> approveRequest(@RequestBody RequestIdStatus appReq) {

        HttpStatus status;

        int count = transReqRepository.changeRequestStatus(appReq.getReqStatus(), appReq.getRequestId());

        if (count > 0) {
            if (appReq.getReqStatus().equalsIgnoreCase("A")) {
                Invoice invoice = new Invoice();
                invoice.setRequestid(appReq.getRequestId());

                invoiceRepository.save(invoice);

                Optional<TransportRequest> value = transReqRepository.findById(appReq.getRequestId());

                TransportRequest transportRequest = value.get();

                List<Driver> allDrivers = driverRepository.findByStatus("UNBLOCK");
                List<String> allDriversEmailIDs = allDrivers.stream().map(Driver::getEmail).collect(Collectors.toList());

                new Thread(() -> {
                    emailToAdmin.sendTransportRequestEmails(allDriversEmailIDs, transportRequest, "A");
                }).start();

            }
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }

    @GetMapping("/request/{requestId}")
    public ResponseEntity<TransRequestCustom> viewRequestById(@PathVariable() Integer requestId) {

        Optional<TransportRequest> value = transReqRepository.findById(requestId);

        if (value.isPresent()) {
            TransportRequest transReq = value.get();
            TransRequestCustom dest = commonUtil.copyRequest(transReq);
            return new ResponseEntity<>(dest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usertype/{usertype}")
    public ResponseEntity<Iterable<TransRequestCustom>> viewRequestByUserType(@PathVariable String usertype) {

        usertype = usertype.toUpperCase();

        Iterable<TransportRequest> allrequests = transReqRepository.findAllByUserType(usertype);

        List<TransRequestCustom> allReq = new ArrayList<>();
        allrequests.forEach(req -> {
            TransRequestCustom dest = commonUtil.copyRequest(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> viewAllRequests() {

        Iterable<TransportRequest> allrequests = transReqRepository.findAll();

        List<TransRequestCustom> allReq = new ArrayList<>();
        allrequests.forEach(req -> {
            TransRequestCustom dest = commonUtil.copyRequest(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    /*
     *   dateformat : yyyy-MM-dd HH:mm:ss
     */
    @PostMapping("/report")
    public ResponseEntity generateReport(@RequestBody SendTransportDocument pdfParam) {

        String usertype = pdfParam.getUsertype().toUpperCase();
        HttpStatus status = null;
        try {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            Date fromDate = null;
            Date toDate = null;

            fromDate = simpleDateFormat.parse(pdfParam.getFromDate());
            toDate = simpleDateFormat.parse(pdfParam.getToDate());

            Iterable<TransportRequest> allrequests = transReqRepository.
                    findAllByUserTypeAndDateTimeBetween(usertype, fromDate, toDate);

            if (allrequests.spliterator().getExactSizeIfKnown() > 0) {
                new Thread(() -> {
                    csv.createCSV(pdfParam, allrequests);
                }).start();
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.NOT_FOUND;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(status);
    }

    private List<String> getAllEmailIDs(List<SignUp> allAdminUsers) {

        return allAdminUsers.stream().map(SignUp::getEmail).collect(Collectors.toList());
    }
}