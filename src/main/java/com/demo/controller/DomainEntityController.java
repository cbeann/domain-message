package com.demo.controller;

import com.demo.dto.ResultDTO;
import com.demo.entity.DomainEntity;
import com.demo.repository.DomainEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/domain_entity")
public class DomainEntityController {

    @Autowired
    private DomainEntityRepository domainEntityRepository;

    @GetMapping
    public String listDomainEntities(Model model) {
        List<DomainEntity> entities = domainEntityRepository.getAll();
        model.addAttribute("entities", entities);
        return "domain_entity";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultDTO listDomainEntitiesList() {
        List<DomainEntity> entities = domainEntityRepository.getAll();
        return ResultDTO.SUCCESS(entities);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultDTO getById(@PathVariable Long id) {
        DomainEntity entity = domainEntityRepository.getById(id);
        return ResultDTO.SUCCESS(entity);
    }

    @GetMapping("/domain/{domainId}")
    @ResponseBody
    public ResultDTO getByDomainId(@PathVariable Long domainId) {
        List<DomainEntity> entities = domainEntityRepository.getByDomainId(domainId);
        return ResultDTO.SUCCESS(entities);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResultDTO add(@RequestBody DomainEntity domainEntity) {
        domainEntityRepository.add(domainEntity);
        return ResultDTO.SUCCESS("添加成功");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultDTO delete(@PathVariable Long id) {
        domainEntityRepository.delete(id);
        return ResultDTO.SUCCESS("删除成功");
    }
}
