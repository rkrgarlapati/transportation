package com.transport.transportation.services;

import com.transport.transportation.entity.Destination;
import com.transport.transportation.entity.DestinationName;
import com.transport.transportation.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/destination")
public class DestinationService {

    private DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody DestinationName destinationName) {

        Destination destination = new Destination();
        destination.setDestinationid(null);
        destination.setDestinationname(destinationName.getDestinationname());

        destinationRepository.save(destination);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Destination destination) {

        destinationRepository.save(destination);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<Destination>> getAll() {
        HttpStatus status;

        Iterable<Destination> all = destinationRepository.findAll();

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

        Optional<Destination> destination = destinationRepository.findById(destinationid);

        if(destination.isPresent()) {
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
