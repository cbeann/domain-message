package com.demo.entity;

public class DomainEventFilterSetting {

    private Long id;

    private Long eventId;

    private String filterKey;

    private String filterValue;

    public String getKey() {
        return filterKey;
    }

    public void setKey(String key) {
        this.filterKey = key;
    }

    public String getValue() {
        return filterValue;
    }

    public void setValue(String value) {
        this.filterValue = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }
}
