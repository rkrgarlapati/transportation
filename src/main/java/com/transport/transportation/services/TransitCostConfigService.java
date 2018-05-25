package com.transport.transportation.services;

import com.transport.transportation.entity.TransitCostConfigEmbed;
import com.transport.transportation.entity.TransitCostConfiguration;
import com.transport.transportation.entity.TransitDestination;
import com.transport.transportation.entity.TransitSource;
import com.transport.transportation.repository.TransitCostConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transit/costconfiguration")
public class TransitCostConfigService {

    private TransitCostConfigRepository costConfigRepository;

    @Autowired
    public TransitCostConfigService(TransitCostConfigRepository costConfigRepository) {
        this.costConfigRepository = costConfigRepository;
    }

    @PostMapping
    public ResponseEntity<?> addCost(@RequestBody TransitCostConfiguration costConfig) {

        TransitCostConfigEmbed costConfigEmbed = getId(costConfig.getSourceid(),
                costConfig.getDestinationid(), costConfig.getTrucksize());

        costConfig.setCostConfigEmbed(costConfigEmbed);

        costConfigRepository.save(costConfig);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{sourceid}/{destination}/{trucksize}")
    public ResponseEntity<?> login(@PathVariable Integer sourceid,
                                   @PathVariable Integer destination,
                                   @PathVariable Double trucksize) {

        TransitCostConfigEmbed costConfigEmbed = getId(sourceid, destination, trucksize);
        Optional<TransitCostConfiguration> value = costConfigRepository.findById(costConfigEmbed);

        if (value.isPresent()) {
            return new ResponseEntity<>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public TransitCostConfigEmbed getId(Integer sourceid, Integer destination, Double trucksize) {
        TransitCostConfigEmbed costConfigEmbed = new TransitCostConfigEmbed();

        TransitDestination dest = new TransitDestination();
        dest.setDestinationid(destination);
        costConfigEmbed.setDestination(dest);

        TransitSource source = new TransitSource();
        source.setSourceid(sourceid);
        costConfigEmbed.setSource(source);

        costConfigEmbed.setTrucksize(trucksize);

        return costConfigEmbed;
    }
}
