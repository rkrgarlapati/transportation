package com.transport.transportation.services;

import com.transport.transportation.common.CommonUtil;
import com.transport.transportation.email.TransitReqSendEmailToAdmin;
import com.transport.transportation.entity.*;
import com.transport.transportation.repository.*;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/transit")
public class TransitService {

    @Autowired
    private TransitRepository transitRepository;

    @Autowired
    private TransitSourceRepository sourceRepository;

    @Autowired
    private TransitDestinationRepository destinationRepository;

    @Autowired
    private TransitServicesRepository transitServicesRepository;

    @Autowired
    private TransitProductsRepository transitProductsRepository;

    @Autowired
    private TransitInvoiceRepository invoiceRepository;

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private TransitReqSendEmailToAdmin emailToAdmin;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CommonUtil commonUtil;

    @PostMapping
    public ResponseEntity<?> transportRequest(@RequestBody TransitRequestPost transReq) {

        TransitRequest transitRequest = new TransitRequest();
        SignUp user = new SignUp();
        TransitDestination dest = new TransitDestination();
        TransitSource source = new TransitSource();
        TransitServices service = new TransitServices();
        TransitProducts products = new TransitProducts();

        dest.setDestinationid(transReq.getDestinationId());
        transitRequest.setDest(dest);

        source.setSourceid(transReq.getSourceId());
        transitRequest.setSour(source);

        Integer serviceId = transReq.getServiceId();

        if (serviceId != null && serviceId > 0) {
            service.setServiceid(serviceId);
            transitRequest.setServi(service);
        }

        Integer productId = transReq.getProductId();
        if (productId != null && productId > 0) {
            products.setProductid(productId);
            transitRequest.setProd(products);
        }

        transitRequest.setCustomclear(transReq.isCustomclearance());
        transitRequest.setTrucksize(transReq.getTrucksize());

        transitRequest.setRequestStatus("P");

        user.setEmail(transReq.getUserid());
        transitRequest.setUser(user);

        transitRequest.setDateTime(transReq.getDateTime());
        transitRequest.setMobileNo(transReq.getMobileNo());
        transitRequest.setCost(transReq.getCost());

        TransitRequest inserted = transitRepository.save(transitRequest);

        Optional<TransitSource> sourceVal = sourceRepository.findById(source.getSourceid());
        source.setSourcename(sourceVal.get().getSourcename());

        Optional<TransitDestination> destiVal = destinationRepository.findById(dest.getDestinationid());
        dest.setDestinationname(destiVal.get().getDestinationname());

        /*if (serviceId != null && serviceId > 0) {
            Optional<TransitServices> transitServices = transitServicesRepository.findById(serviceId);
            service.setServicename(transitServices.get().getServicename());
        }

        if (productId != null && productId > 0) {
            Optional<TransitProducts> transitProducts = transitProductsRepository.findById(productId);
            products.setProductname(transitProducts.get().getProductname());
        }*/

        List<SignUp> allAdminUsers = signUpRepository.findByUsertype("ADMIN");
        List<String> allAdminEmailIDs = getAllEmailIDs(allAdminUsers);

        if (inserted != null) {
            new Thread(() -> {
                emailToAdmin.sendTransitRequestEmails(allAdminEmailIDs, inserted, "P");
            }).start();
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{requestId}/{reqStatus}")
    @Transactional
    public ResponseEntity<?> approveRequest(@PathVariable Integer requestId,
                                            @PathVariable String reqStatus) {

        HttpStatus status;

        int count = transitRepository.changeRequestStatus(reqStatus, requestId);

        if (count > 0) {
            if (reqStatus.equalsIgnoreCase("A")) {
                TransitInvoice invoice = new TransitInvoice();
                invoice.setRequestid(requestId);

                invoiceRepository.save(invoice);

                Optional<TransitRequest> value = transitRepository.findById(requestId);
                TransitRequest transitRequest = value.get();

                List<Driver> allDrivers = driverRepository.findByStatus("UNBLOCK");
                List<String> allDriversEmailIDs = allDrivers.stream().map(Driver::getEmail).collect(Collectors.toList());

                new Thread(() -> {
                    emailToAdmin.sendTransitRequestEmails(allDriversEmailIDs, transitRequest, "A");
                }).start();
            }
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }

    @PatchMapping("/{requestId}/warehouse/{warehousecharges}")
    public ResponseEntity<?> updateWarehousecharges(@PathVariable Integer requestId,
                                                    @PathVariable Double warehousecharges) {

        HttpStatus status;
        int count = 0;

        if (warehousecharges != null && warehousecharges > 0) {
            count = transitRepository.changeWarehousecharges(warehousecharges, requestId);
        }

        if (count > 0) {
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<TransitCustom> viewRequestById(@PathVariable() Integer requestId) {

        Optional<TransitRequest> value = transitRepository.findById(requestId);

        if (value.isPresent()) {
            TransitRequest transReq = value.get();
            TransitCustom dest = copyRequest(transReq);
            return new ResponseEntity<>(dest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/requestStatus/{requestStatus}")
    public ResponseEntity<Iterable<TransitRequestCustom>> viewRequestsByRequestStatus(@PathVariable String requestStatus) {

        requestStatus = requestStatus.toUpperCase();

        Iterable<TransitRequest> allrequests = transitRepository.findAllByRequestStatus(requestStatus);

        List<TransitRequestCustom> allReq = new ArrayList<>();

        allrequests.forEach(req -> {
            TransitRequestCustom dest = commonUtil.copyTransitRequest(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> viewAllRequests() {

        Iterable<TransitRequest> allrequests = transitRepository.findAll();

        List<TransitRequest> allReq = new ArrayList<>();
        allrequests.forEach(req -> {
            TransitCustom dest = copyRequest(req);
            allReq.add(dest);
        });

        return new ResponseEntity<>(allReq, HttpStatus.OK);
    }

    private TransitCustom copyRequest(TransitRequest transReq) {
        TransitCustom dest = new TransitCustom();

        BeanUtils.copyProperties(transReq, dest);

        dest.setDestination(transReq.getDest().getDestinationname());
        dest.setSource(transReq.getSour().getSourcename());
        dest.setSourceId(null);
        dest.setDestinationId(null);
        /*User user = transReq.getUser();
        dest.setUserFullName(user.getFirstName().concat(" ").concat(user.getLastName()));*/

        return dest;
    }

    private List<String> getAllEmailIDs(List<SignUp> allAdminUsers) {

        return allAdminUsers.stream().map(SignUp::getEmail).collect(Collectors.toList());
    }
}