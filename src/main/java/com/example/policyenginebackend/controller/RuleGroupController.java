package com.example.policyenginebackend.controller;

import com.example.policyenginebackend.model.RuleGroup;
import com.example.policyenginebackend.repository.RuleGroupRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rule-groups")
@CrossOrigin
public class RuleGroupController {

    private final RuleGroupRepository repository;

    public RuleGroupController(RuleGroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RuleGroup> getAllRuleGroups() {
        return repository.findAll();
    }

    @PostMapping
    public RuleGroup createRuleGroup(@RequestBody RuleGroup group) {
        return repository.save(group);
    }
}
