package com.transport.transportation.repository;

import com.transport.transportation.entity.CostConfigEmbed;
import com.transport.transportation.entity.CostConfiguration;
import org.springframework.data.repository.CrudRepository;

public interface CostConfigRepository  extends CrudRepository<CostConfiguration, CostConfigEmbed> {
}
