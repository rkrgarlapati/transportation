package com.transport.transportation.services;

import com.transport.transportation.entity.Source;
import com.transport.transportation.entity.SourceName;
import com.transport.transportation.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/source")
public class SourceService {

    private SourceRepository sourceRepository;

    @Autowired
    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SourceName sourceName) {

        Source source = new Source();
        source.setSourceid(null);
        source.setSourcename(sourceName.getSourcename());

        sourceRepository.save(source);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Source source) {

        sourceRepository.save(source);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Source>> getAll() {
        List<Source> sourceList = new ArrayList<>();
        HttpStatus status;

        Iterable<Source> all = sourceRepository.findAll();
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

        Optional<Source> source = sourceRepository.findById(sourceid);

        if(source.isPresent()) {
            return new ResponseEntity<>(source, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
