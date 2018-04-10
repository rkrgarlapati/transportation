package com.transport.transportation.services;

import com.transport.transportation.entity.Destination;
import com.transport.transportation.entity.DestinationName;
import com.transport.transportation.entity.Source;
import com.transport.transportation.repository.DestinationRepository;
import com.transport.transportation.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
}
