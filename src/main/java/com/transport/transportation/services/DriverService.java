package com.transport.transportation.services;

import com.transport.transportation.dto.DriverBlockUnblock;
import com.transport.transportation.entity.Driver;
import com.transport.transportation.entity.SignUp;
import com.transport.transportation.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/driver")
public class DriverService {

    private DriverRepository driverRepository;

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
}
