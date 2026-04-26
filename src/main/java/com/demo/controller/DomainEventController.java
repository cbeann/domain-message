package com.demo.controller;

import com.demo.dto.ResultDTO;
import com.demo.entity.DomainEvent;
import com.demo.repository.DomainEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/domain_event")
public class DomainEventController {

    @Autowired
    private DomainEventRepository domainEventRepository;

    @GetMapping
    public String listDomainEvents(Model model) {
        List<DomainEvent> events = domainEventRepository.getAll();
        model.addAttribute("events", events);
        return "domain_event";
    }

    @GetMapping("/{datasourceId}")
    @ResponseBody
    public ResultDTO getByDataSourceId(@PathVariable Long datasourceId) {
        List<DomainEvent> events = domainEventRepository.getByDatasourceId(datasourceId);
        return ResultDTO.SUCCESS(events);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResultDTO add(@RequestBody DomainEvent domainEvent) {
        domainEventRepository.add(domainEvent);
        return ResultDTO.SUCCESS("添加成功");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultDTO delete(@PathVariable Long id) {
        domainEventRepository.delete(id);
        return ResultDTO.SUCCESS("删除成功");
    }
}
