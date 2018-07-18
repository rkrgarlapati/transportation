package com.transport.transportation.services;

import com.transport.transportation.email.TransitReqSendEmailToAdmin;
import com.transport.transportation.entity.SignUp;
import com.transport.transportation.entity.TransitCustomDocs;
import com.transport.transportation.entity.TransitCustomsDocsName;
import com.transport.transportation.entity.TransitRequest;
import com.transport.transportation.repository.SignUpRepository;
import com.transport.transportation.repository.TransitCustomsDocsRepository;
import com.transport.transportation.repository.TransitRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/document")
public class TransitCustomsDocsService {

    @Autowired
    private TransitCustomsDocsRepository docsRepository;

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private TransitRepository transitRepository;

    @Autowired
    private TransitReqSendEmailToAdmin transitReqSendEmailToAdmin;

    @PostMapping
    public ResponseEntity<Object> document(@RequestBody TransitCustomsDocsName docsrequest) {

        TransitCustomDocs docs = new TransitCustomDocs();
        BeanUtils.copyProperties(docsrequest, docs);

        docsRepository.save(docs);

        if(StringUtils.isNotEmpty(docsrequest.getUsertype())){
            sendMail(docsrequest);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{requestid}")
    public ResponseEntity<List<TransitCustomDocs>> document(@PathVariable Integer requestid) {

        List<TransitCustomDocs> allDocs = docsRepository.findAllByRequestid(requestid);

        return new ResponseEntity<>(allDocs, HttpStatus.CREATED);
    }

    public void sendMail(TransitCustomsDocsName docsrequest) {

        new Thread(() -> {
            Optional<TransitRequest> transitRequest = transitRepository.findById(docsrequest.getRequestid());
            TransitRequest request = transitRequest.get();

            String useremailid = request.getUser().getEmail();

            List<SignUp> allAdminUsers = signUpRepository.findByUsertype("ADMIN");
            List<String> allAdminEmailIDs = allAdminUsers.stream().map(SignUp::getEmail).collect(Collectors.toList());

            allAdminEmailIDs.add(useremailid);


            transitReqSendEmailToAdmin.sendMailToCustomerAndAdminCustClearance(allAdminEmailIDs, docsrequest);
        }).start();
    }

}
