package com.transport.transportation.services;

import com.transport.transportation.entity.Destination;
import com.transport.transportation.entity.DestinationName;
import com.transport.transportation.entity.TransitDestination;
import com.transport.transportation.entity.TransitDestinationName;
import com.transport.transportation.repository.DestinationRepository;
import com.transport.transportation.repository.TransitDestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transit/destination")
public class TransitDestinationService {

    private TransitDestinationRepository destinationRepository;

    @Autowired
    public TransitDestinationService(TransitDestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TransitDestinationName destinationName) {

        TransitDestination destination = new TransitDestination();
        destination.setDestinationid(null);
        destination.setDestinationname(destinationName.getDestinationname());

        destinationRepository.save(destination);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody TransitDestination destination) {

        destinationRepository.save(destination);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<TransitDestination>> getAll() {
        HttpStatus status;

        Iterable<TransitDestination> all = destinationRepository.findAll();

        if (all.spliterator().getExactSizeIfKnown() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(all, status);
    }


    @DeleteMapping("/{destinationid}")
    public ResponseEntity<?> delete(@PathVariable Integer destinationid) {

        destinationRepository.deleteById(destinationid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{destinationid}")
    public ResponseEntity<?> getById(@PathVariable Integer destinationid) {

        Optional<TransitDestination> destination = destinationRepository.findById(destinationid);

        if(destination.isPresent()) {
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
