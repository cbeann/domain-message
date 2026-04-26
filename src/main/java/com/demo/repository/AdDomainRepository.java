package com.demo.repository;

import com.demo.entity.AdDomain;
import com.demo.mapper.AdDomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdDomainRepository {

    @Autowired
    private AdDomainMapper adDomainMapper;

    public List<AdDomain> getAll() {
        return adDomainMapper.getAll();
    }

    public void add(AdDomain adDomain) {
        adDomain.setGmtCreate(new Date());
        adDomain.setGmtModifed(new Date());
        adDomainMapper.add(adDomain);
    }

    public AdDomain getById(Long id) {
        return adDomainMapper.getById(id);
    }

    public void delete(Long id) {
        adDomainMapper.delete(id);
    }
}
