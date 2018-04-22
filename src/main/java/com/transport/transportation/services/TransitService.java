package com.transport.transportation.services;

import com.transport.transportation.entity.*;
import com.transport.transportation.repository.InvoiceRepository;
import com.transport.transportation.repository.TransitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*@RestController
@RequestMapping("/transit")*/
public class TransitService {

    @Autowired
    private TransitRepository transitRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping
    public ResponseEntity<?> transportRequest(@RequestBody TransitRequest transReq) {

        Destination dest = new Destination();
        dest.setDestinationid(transReq.getDestinationId());
        transReq.setDest(dest);

        Source source = new Source();
        source.setSourceid(transReq.getSourceId());
        transReq.setSour(source);

        transReq.setRequestStatus("P");

        User user = new User();
        user.setUsername(transReq.getUsername());
        user.setUserType(transReq.getUserType());
        transReq.setUser(user);

        transitRepository.save(transReq);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{requestId}/{reqStatus}")
    public ResponseEntity<?> approveRequest(@PathVariable Integer requestId,
                                            @PathVariable String reqStatus) {

        HttpStatus status;

        int count = transitRepository.changeRequestStatus(reqStatus, requestId);

        if (count > 0) {
            if (reqStatus.equalsIgnoreCase("A")) {
                Invoice invoice = new Invoice();
                invoice.setRequestId(requestId);

                invoiceRepository.save(invoice);
            }
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

    @GetMapping("/usertype/{usertype}")
    public ResponseEntity<TransitCustom> viewRequestByUserType(@PathVariable String usertype) {

        usertype = usertype.toUpperCase();

        Optional<TransitRequest> value = transitRepository.findAllByUserType(usertype);

        if (value.isPresent()) {
            TransitRequest transReq = value.get();
            TransitCustom dest = copyRequest(transReq);
            return new ResponseEntity<>(dest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
        User user = transReq.getUser();
        dest.setUserFullName(user.getFirstName().concat(" ").concat(user.getLastName()));

        return dest;
    }

}