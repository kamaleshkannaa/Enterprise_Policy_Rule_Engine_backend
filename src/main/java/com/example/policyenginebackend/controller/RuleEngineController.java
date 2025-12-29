package com.example.policyenginebackend.controller;

import com.example.policyenginebackend.service.RuleEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/engine")
@CrossOrigin(origins = "*")
public class RuleEngineController {

    private final RuleEngineService ruleEngineService;

    public RuleEngineController(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }

    @PostMapping("/evaluate")
    public Map<String, String> evaluate(@RequestBody Map<String, Object> input) {
        String decision = ruleEngineService.evaluate(input);
        return Map.of("decision", decision);
    }

}
