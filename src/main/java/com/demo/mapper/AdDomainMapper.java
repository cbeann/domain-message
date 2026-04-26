package com.demo.mapper;

import com.demo.entity.AdDomain;
import java.util.List;

public interface AdDomainMapper {

    List<AdDomain> getAll();

    void add(AdDomain adDomain);

    AdDomain getById(Long id);

    void delete(Long id);
}
