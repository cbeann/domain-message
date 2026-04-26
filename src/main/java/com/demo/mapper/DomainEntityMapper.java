package com.demo.mapper;

import com.demo.entity.DomainEntity;
import java.util.List;

public interface DomainEntityMapper {

    List<DomainEntity> getAll();

    void add(DomainEntity domainEntity);

    DomainEntity getById(Long id);

    void delete(Long id);

    List<DomainEntity> getByDomainId(Long domainId);
}
