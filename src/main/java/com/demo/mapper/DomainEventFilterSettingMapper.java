package com.demo.mapper;

import com.demo.entity.DomainEventFilterSetting;
import java.util.List;

public interface DomainEventFilterSettingMapper {

    List<DomainEventFilterSetting> getByEventId(Long eventId);

    void add(DomainEventFilterSetting filterSetting);

    void deleteByEventId(Long eventId);
}
