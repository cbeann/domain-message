package com.demo.entity;

import com.demo.entity.common.CommonDomain;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AdDomain extends CommonDomain {


    private String name;

    private String description;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
