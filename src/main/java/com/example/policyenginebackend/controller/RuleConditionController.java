package com.example.policyenginebackend.controller;

import com.example.policyenginebackend.model.Rule;
import com.example.policyenginebackend.model.RuleCondition;
import com.example.policyenginebackend.controller.CreateConditionRequest;
import com.example.policyenginebackend.repository.RuleConditionRepository;
import com.example.policyenginebackend.repository.RuleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rules/{ruleId}/conditions")

public class RuleConditionController {

    private final RuleConditionRepository conditionRepository;
    private final RuleRepository ruleRepository;

    public RuleConditionController(
            RuleConditionRepository conditionRepository,
            RuleRepository ruleRepository
    ) {
        this.conditionRepository = conditionRepository;
        this.ruleRepository = ruleRepository;
    }

    // ✅ CREATE CONDITION
    @PostMapping
    public RuleCondition createCondition(
            @PathVariable Long ruleId,
            @RequestBody CreateConditionRequest request
    ) {

        if (request.getFieldName() == null ||
                request.getOperator() == null ||
                request.getFieldValue() == null) {
            throw new IllegalArgumentException("Missing required condition fields");
        }

        Rule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        RuleCondition condition = new RuleCondition();
        condition.setFieldName(request.getFieldName());
        condition.setOperator(request.getOperator());
        condition.setFieldValue(request.getFieldValue());
        condition.setRule(rule);

        return conditionRepository.save(condition);
    }

    // ✅ GET CONDITIONS BY RULE
    @GetMapping
    public List<RuleCondition> getByRule(@PathVariable Long ruleId) {
        return conditionRepository.findByRuleId(ruleId);
    }

    // ✅ DELETE CONDITION
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long ruleId,
            @PathVariable Long id
    ) {
        int deleted = conditionRepository.deleteByIdAndRuleId(id, ruleId);

        if (deleted == 0) {
            return ResponseEntity
                    .badRequest()
                    .body("Condition not found for this rule");
        }

        return ResponseEntity.ok("Condition deleted successfully");
    }
}
