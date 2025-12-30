//package com.example.policyenginebackend.controller;
//
//import com.example.policyenginebackend.model.Rule;
//import com.example.policyenginebackend.repository.RuleRepository;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/rules")
//@CrossOrigin
//public class RuleController {
//
//    private final RuleRepository ruleRepository;
//
//    public RuleController(RuleRepository ruleRepository) {
//        this.ruleRepository = ruleRepository;
//    }
//
//    // ===============================
//    // 1️⃣ CREATE RULE
//    // ===============================
//    @PostMapping
//    public Rule createRule(@RequestBody Rule rule) {
//        return ruleRepository.save(rule);
//    }
//
//    // ===============================
//    // 2️⃣ GET ALL RULES
//    // ===============================
//    @GetMapping
//    public List<Rule> getAllRules() {
//        return ruleRepository.findAll();
//    }
//
//    // ===============================
//    // 3️⃣ GET RULE BY ID
//    // ===============================
//    @GetMapping("/{id}")
//    public Rule getRuleById(@PathVariable Long id) {
//        return ruleRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Rule not found"));
//    }
//
//    // ===============================
//    // 4️⃣ ACTIVATE / DEACTIVATE RULE
//    // ===============================
//    @PutMapping("/{id}/active/{status}")
//    public Rule updateRuleStatus(
//            @PathVariable Long id,
//            @PathVariable boolean status
//    ) {
//        Rule rule = ruleRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Rule not found"));
//
//        rule.setActive(status);
//        return ruleRepository.save(rule);
//    }
//
//    // ===============================
//    // 5️⃣ DELETE RULE
//    // (Conditions auto-deleted)
//    // ===============================
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void deleteRule(@PathVariable Long id) {
//
//        // 🔥 delete child records FIRST
//        conditionRepository.deleteByRuleId(id);
//
//        // 🔥 then delete parent
//        ruleRepository.deleteById(id);
//    }
//}

package com.example.policyenginebackend.controller;

import com.example.policyenginebackend.model.Rule;
import com.example.policyenginebackend.repository.RuleConditionRepository;
import com.example.policyenginebackend.repository.RuleRepository;
import com.example.policyenginebackend.service.RuleEngineService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    private final RuleRepository ruleRepository;
    private final RuleConditionRepository conditionRepository;
    private final RuleEngineService ruleEngineService;

    // ✅ FIXED CONSTRUCTOR
    public RuleController(
            RuleRepository ruleRepository,
            RuleConditionRepository conditionRepository,
            RuleEngineService ruleEngineService
    ) {
        this.ruleRepository = ruleRepository;
        this.conditionRepository = conditionRepository;
        this.ruleEngineService = ruleEngineService;
    }

    // ===============================
    // 1️⃣ CREATE RULE
    // ===============================
    @PostMapping
    public Rule createRule(@RequestBody Rule rule) {
        return ruleRepository.save(rule);
    }
    // ===============================
// UPDATE RULE  ✅ REQUIRED
// ===============================
    @PutMapping("/{id}")
    public Rule updateRule(
            @PathVariable Long id,
            @RequestBody Rule updatedRule
    ) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        rule.setName(updatedRule.getName());
        rule.setDescription(updatedRule.getDescription());
        rule.setPriority(updatedRule.getPriority());
        rule.setActive(updatedRule.isActive());
        rule.setGroupId(updatedRule.getGroupId());

        return ruleRepository.save(rule);
    }
    


    // ===============================
    // 2️⃣ GET ALL RULES
    // ===============================
    @GetMapping
    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    // ===============================
    // 3️⃣ GET RULE BY ID
    // ===============================
    @GetMapping("/{id}")
    public Rule getRuleById(@PathVariable Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    // ===============================
    // 4️⃣ ACTIVATE / DEACTIVATE RULE
    // ===============================
    @PutMapping("/{id}/active/{status}")
    public Rule updateRuleStatus(
            @PathVariable Long id,
            @PathVariable boolean status
    ) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        rule.setActive(status);
        return ruleRepository.save(rule);
    }

    // ===============================
    // 5️⃣ DELETE RULE
    // ===============================
    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Long id) {
        ruleRepository.deleteById(id);
    }


    // ===============================
    // 6️⃣ EVALUATE RULE ENGINE ✅
    // ===============================
    @PostMapping("/evaluate")
    public String evaluate(@RequestBody Map<String, Object> input) {
        return ruleEngineService.evaluate(input);
    }
}


