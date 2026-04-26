package com.demo.repository;

import com.demo.entity.DomainDataSource;
import com.demo.entity.DomainEvent;
import com.demo.entity.DomainEventFilterSetting;
import com.demo.mapper.DomainEventFilterSettingMapper;
import com.demo.mapper.DomainEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainEventRepository {

    @Autowired
    private DomainEventMapper domainEventMapper;

    @Autowired
    private DomainEventFilterSettingMapper domainEventFilterSettingMapper;

    @Autowired
    private DomainDataSourceRepository domainDataSourceRepository;

    public List<DomainEvent> getEventByDbTable(String dbName, String tbName) {
        List<DomainDataSource> domainDataSourceList = domainDataSourceRepository.getByDbTable(dbName, tbName);
        List<Long> domainDataSourceIdList = domainDataSourceList.stream().map(x -> x.getId()).collect(Collectors.toList());
        List<DomainEvent> allEvents = domainEventMapper.getAll();
        return allEvents.stream()
                .filter(x -> domainDataSourceIdList.contains(x.getDatasourceId()))
                .collect(Collectors.toList());
    }

    public List<DomainEvent> getAll() {
        return domainEventMapper.getAll();
    }

    @Transactional
    public void add(DomainEvent domainEvent) {
        domainEvent.setGmtCreate(new Date());
        domainEvent.setGmtModifed(new Date());
        domainEventMapper.add(domainEvent);

        if (domainEvent.getDomainEventFilterSettingList() != null) {
            for (DomainEventFilterSetting setting : domainEvent.getDomainEventFilterSettingList()) {
                setting.setEventId(domainEvent.getId());
                domainEventFilterSettingMapper.add(setting);
            }
        }
    }

    @Transactional
    public void delete(Long id) {
        domainEventFilterSettingMapper.deleteByEventId(id);
        domainEventMapper.delete(id);
    }

    public DomainEvent getById(Long id) {
        return domainEventMapper.getById(id);
    }

    public List<DomainEvent> getByDatasourceId(Long datasourceId) {
        return domainEventMapper.getByDatasourceId(datasourceId);
    }
}
