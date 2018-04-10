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
}
