package com.transport.transportation.services;

import com.transport.transportation.entity.Service;
import com.transport.transportation.entity.ServiceName;
import com.transport.transportation.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServicesService {

    private ServicesRepository serviceRepository;

    @Autowired
    public ServicesService(ServicesRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ServiceName serviceName) {

        Service service = new Service();
        service.setServiceid(null);
        service.setServicename(serviceName.getServicename());

        serviceRepository.save(service);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Service service) {

        serviceRepository.save(service);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<Service>> getAll() {
        HttpStatus status;

        Iterable<Service> all = serviceRepository.findAll();

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

        Optional<Service> service = serviceRepository.findById(serviceid);

        if(service.isPresent()) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
