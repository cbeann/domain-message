package com.demo.mapper;

import com.demo.entity.DomainEvent;
import java.util.List;

public interface DomainEventMapper {

    List<DomainEvent> getAll();

    void add(DomainEvent domainEvent);

    DomainEvent getById(Long id);

    void delete(Long id);

    List<DomainEvent> getByDatasourceId(Long datasourceId);
}
