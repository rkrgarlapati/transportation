package com.transport.transportation.services;

import com.transport.transportation.entity.TransitServices;
import com.transport.transportation.entity.TransitServiceName;
import com.transport.transportation.repository.TransitServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/service")
public class TransitServicesService {

    private TransitServicesRepository serviceRepository;

    @Autowired
    public TransitServicesService(TransitServicesRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TransitServiceName transitServiceName) {

        TransitServices transitServices = new TransitServices();
        transitServices.setServiceid(null);
        transitServices.setServicename(transitServiceName.getServicename());

        serviceRepository.save(transitServices);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody TransitServices transitServices) {

        serviceRepository.save(transitServices);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<TransitServices>> getAll() {
        HttpStatus status;

        Iterable<TransitServices> all = serviceRepository.findAll();

        if (all.spliterator().getExactSizeIfKnown() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(all, status);
    }


    @DeleteMapping("/{serviceid}")
    public ResponseEntity<?> delete(@PathVariable Integer serviceid) {

        serviceRepository.deleteById(serviceid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{serviceid}")
    public ResponseEntity<?> getById(@PathVariable Integer serviceid) {

        Optional<TransitServices> service = serviceRepository.findById(serviceid);

        if(service.isPresent()) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
