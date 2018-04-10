package com.transport.transportation.services;

import com.transport.transportation.entity.CostConfigEmbed;
import com.transport.transportation.entity.CostConfiguration;
import com.transport.transportation.entity.Destination;
import com.transport.transportation.entity.Source;
import com.transport.transportation.repository.CostConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/costconfiguration")
public class CostConfigService {

    private CostConfigRepository costConfigRepository;

    @Autowired
    public CostConfigService(CostConfigRepository costConfigRepository) {
        this.costConfigRepository = costConfigRepository;
    }

    @PostMapping
    public ResponseEntity<?> addCost(@RequestBody CostConfiguration costConfig) {

        CostConfigEmbed costConfigEmbed = getId(costConfig.getSourceid(),
                costConfig.getDestinationid());

        costConfig.setCostConfigEmbed(costConfigEmbed);

        costConfigRepository.save(costConfig);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{sourceid}/{destination}")
    public ResponseEntity<?> login(@PathVariable Integer sourceid,
                                   @PathVariable Integer destination) {

        CostConfigEmbed costConfigEmbed = getId(sourceid, destination);
        Optional<CostConfiguration> value = costConfigRepository.findById(costConfigEmbed);

        if (value.isPresent()) {
            return new ResponseEntity<>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public CostConfigEmbed getId(Integer sourceid, Integer destination) {
        CostConfigEmbed costConfigEmbed = new CostConfigEmbed();

        Destination dest = new Destination();
        dest.setDestinationid(destination);
        costConfigEmbed.setDestination(dest);

        Source source = new Source();
        source.setSourceid(sourceid);
        costConfigEmbed.setSource(source);

        return costConfigEmbed;
    }
}
