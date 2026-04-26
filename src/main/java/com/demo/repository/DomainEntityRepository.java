package com.demo.repository;

import com.demo.entity.DomainEntity;
import com.demo.mapper.DomainEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DomainEntityRepository {

    @Autowired
    private DomainEntityMapper domainEntityMapper;

    public List<DomainEntity> getAll() {
        return domainEntityMapper.getAll();
    }

    public void add(DomainEntity domainEntity) {
        domainEntity.setGmtCreate(new Date());
        domainEntity.setGmtModifed(new Date());
        domainEntityMapper.add(domainEntity);
    }

    public DomainEntity getById(Long id) {
        return domainEntityMapper.getById(id);
    }

    public void delete(Long id) {
        domainEntityMapper.delete(id);
    }

    public List<DomainEntity> getByDomainId(Long domainId) {
        return domainEntityMapper.getByDomainId(domainId);
    }
}
