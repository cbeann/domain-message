package com.demo.controller;

import com.demo.dto.ResultDTO;
import com.demo.entity.AdDomain;
import com.demo.repository.AdDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ad_domain")
public class AdDomainController {

    @Autowired
    private AdDomainRepository adDomainRepository;

    @GetMapping
    public String listAdDomains(Model model) {
        List<AdDomain> domains = adDomainRepository.getAll();
        model.addAttribute("domains", domains);
        return "ad_domain";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultDTO listAdDomains() {
        List<AdDomain> domains = adDomainRepository.getAll();
        return ResultDTO.SUCCESS(domains);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultDTO getById(@PathVariable Long id) {
        AdDomain domain = adDomainRepository.getById(id);
        return ResultDTO.SUCCESS(domain);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResultDTO add(@RequestBody AdDomain adDomain) {
        adDomainRepository.add(adDomain);
        return ResultDTO.SUCCESS("添加成功");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultDTO delete(@PathVariable Long id) {
        adDomainRepository.delete(id);
        return ResultDTO.SUCCESS("删除成功");
    }
}
