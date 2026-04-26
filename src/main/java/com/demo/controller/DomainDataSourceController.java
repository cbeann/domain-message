package com.demo.controller;

import com.demo.dto.ResultDTO;
import com.demo.entity.DomainDataSource;
import com.demo.repository.DomainDataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/domain_datasource")
public class DomainDataSourceController {

    @Autowired
    private DomainDataSourceRepository domainDataSourceRepository;

    @GetMapping
    public String listDomainDataSources(Model model) {
        List<DomainDataSource> sources = domainDataSourceRepository.getAll();
        model.addAttribute("sources", sources);
        return "domain_datasource";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultDTO listDomainDataSourcesList() {
        List<DomainDataSource> sources = domainDataSourceRepository.getAll();
        return ResultDTO.SUCCESS(sources);
    }

    @GetMapping("/{domainEntityId}")
    @ResponseBody
    public ResultDTO getByDomainEntityId(@PathVariable Long domainEntityId) {
        List<DomainDataSource> sources = domainDataSourceRepository.getByDomainEntityId(domainEntityId);
        return ResultDTO.SUCCESS(sources);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResultDTO add(@RequestBody DomainDataSource domainDataSource) {
        domainDataSourceRepository.add(domainDataSource);
        return ResultDTO.SUCCESS("添加成功");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultDTO delete(@PathVariable Long id) {
        domainDataSourceRepository.delete(id);
        return ResultDTO.SUCCESS("删除成功");
    }
}
