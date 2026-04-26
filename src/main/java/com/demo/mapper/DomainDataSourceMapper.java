package com.demo.mapper;

import com.demo.entity.DomainDataSource;
import java.util.List;

public interface DomainDataSourceMapper {

    List<DomainDataSource> getAll();

    void add(DomainDataSource domainDataSource);

    DomainDataSource getById(Long id);

    void delete(Long id);

    List<DomainDataSource> getByDbTable(String dbName, String tbName);

    List<DomainDataSource> getByDomainEntityId(Long domainEntityId);
}
