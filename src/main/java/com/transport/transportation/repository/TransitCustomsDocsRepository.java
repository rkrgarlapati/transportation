package com.transport.transportation.repository;

import com.transport.transportation.entity.TransitCustomDocs;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransitCustomsDocsRepository extends CrudRepository<TransitCustomDocs, Integer> {

    List<TransitCustomDocs> findAllByRequestid(Integer requestid);
}
