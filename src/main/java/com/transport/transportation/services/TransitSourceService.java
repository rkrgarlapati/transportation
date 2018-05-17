package com.transport.transportation.services;

import com.transport.transportation.entity.Source;
import com.transport.transportation.entity.SourceName;
import com.transport.transportation.entity.TransitSource;
import com.transport.transportation.repository.SourceRepository;
import com.transport.transportation.repository.TransitSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transit/source")
public class TransitSourceService {

    private TransitSourceRepository sourceRepository;

    @Autowired
    public TransitSourceService(TransitSourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SourceName sourceName) {

        TransitSource source = new TransitSource();
        source.setSourceid(null);
        source.setSourcename(sourceName.getSourcename());

        sourceRepository.save(source);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody TransitSource source) {

        sourceRepository.save(source);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<TransitSource>> getAll() {
        List<TransitSource> sourceList = new ArrayList<>();
        HttpStatus status;

        Iterable<TransitSource> all = sourceRepository.findAll();
        all.forEach(sourceList::add);

        if (sourceList.size() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(sourceList, status);
    }

    @DeleteMapping("/{sourceid}")
    public ResponseEntity<?> delete(@PathVariable Integer sourceid) {

        sourceRepository.deleteById(sourceid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{sourceid}")
    public ResponseEntity<?> getById(@PathVariable Integer sourceid) {

        Optional<TransitSource> source = sourceRepository.findById(sourceid);

        if(source.isPresent()) {
            return new ResponseEntity<>(source, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
