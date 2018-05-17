package com.transport.transportation.repository;

import com.transport.transportation.entity.TransitCostConfigEmbed;
import com.transport.transportation.entity.TransitCostConfiguration;
import org.springframework.data.repository.CrudRepository;

public interface TransitCostConfigRepository extends CrudRepository<TransitCostConfiguration, TransitCostConfigEmbed> {
}
