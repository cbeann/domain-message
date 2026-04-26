package com.demo.repository;

import com.demo.entity.DomainDataSource;
import com.demo.mapper.DomainDataSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DomainDataSourceRepository {

    @Autowired
    private DomainDataSourceMapper domainDataSourceMapper;

    public List<DomainDataSource> getAll() {
        return domainDataSourceMapper.getAll();
    }

    public void add(DomainDataSource domainDataSource) {
        domainDataSource.setGmtCreate(new Date());
        domainDataSource.setGmtModifed(new Date());
        domainDataSourceMapper.add(domainDataSource);
    }

    public DomainDataSource getById(Long id) {
        return domainDataSourceMapper.getById(id);
    }

    public void delete(Long id) {
        domainDataSourceMapper.delete(id);
    }

    public List<DomainDataSource> getByDbTable(String dbName, String tbName) {
        return domainDataSourceMapper.getByDbTable(dbName, tbName);
    }

    public List<DomainDataSource> getByDomainEntityId(Long domainEntityId) {
        return domainDataSourceMapper.getByDomainEntityId(domainEntityId);
    }
}
