package com.transport.transportation.services;

import com.transport.transportation.entity.CompanyAddress;
import com.transport.transportation.repository.CompanyAddressRepository;
import com.transport.transportation.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companyaddress")
public class CompanyAddressService {

    @Autowired
    private CompanyAddressRepository addressRepository;

    @Autowired
    private SignUpRepository signUpRepository;

    @GetMapping("/{addressId}")
    private ResponseEntity<?> companyAddress(@PathVariable int addressId) {

        Optional<CompanyAddress> address = addressRepository.findById(addressId);

        if (address.isPresent()) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    private ResponseEntity<?> allCompanyAddress() {
        Iterable<CompanyAddress> allCompanies = addressRepository.findAll();
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }

    /*@GetMapping
    private ResponseEntity<?> allCompanyAddress() {
        List<String> allCompanies = signUpRepository.getCompanies();
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }*/
}
