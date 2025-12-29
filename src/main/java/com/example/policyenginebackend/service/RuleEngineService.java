//package com.example.policyenginebackend.service;
//
//import com.example.policyenginebackend.model.DecisionLog;
//import com.example.policyenginebackend.model.Rule;
//import com.example.policyenginebackend.model.RuleCondition;
//import com.example.policyenginebackend.repository.DecisionLogRepository;
//import com.example.policyenginebackend.repository.RuleConditionRepository;
//import com.example.policyenginebackend.repository.RuleGroupRepository;
//import com.example.policyenginebackend.repository.RuleRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class RuleEngineService {
//
//    private final RuleRepository ruleRepository;
//    private final RuleConditionRepository conditionRepository;
//    private final DecisionLogRepository logRepository;
//
//    public RuleEngineService(
//            RuleRepository ruleRepository,
//            RuleConditionRepository conditionRepository,
//            DecisionLogRepository logRepository) {
//        this.ruleRepository = ruleRepository;
//        this.conditionRepository = conditionRepository;
//        this.logRepository = logRepository;
//    }
//
//    public String evaluate(Map<String, Object> inputData) {
//
//        List<Rule> rules =
//                ruleRepository.findByActiveTrueOrderByPriorityDesc();
//
//        for (Rule rule : rules) {
//
//            List<RuleCondition> conditions =
//                    conditionRepository.findByRuleId(rule.getId());
//
//            // 🚨 IMPORTANT FIX
//            if (conditions.isEmpty()) {
//                continue; // skip rule with no conditions
//            }
//
//            boolean matched = true;
//
//            for (RuleCondition condition : conditions) {
//
//                Object inputValue =
//                        inputData.get(condition.getFieldName());
//
//                if (inputValue == null ||
//                        !evaluateCondition(inputValue.toString(), condition)) {
//                    matched = false;
//                    break;
//                }
//            }
//
//            if (matched) {
//                saveLog(inputData, rule.getName());
//                return rule.getName();
//            }
//        }
//
//        saveLog(inputData, "NO_MATCH");
//        return "NO_MATCH";
//    }
//
//
//    private boolean evaluateCondition(
//            String inputValue,
//            RuleCondition condition) {
//
//        try {
//            int input = Integer.parseInt(inputValue);
//            int ruleValue =
//                    Integer.parseInt(condition.getFieldValue());
//
//            return switch (condition.getOperator()) {
//                case ">" -> input > ruleValue;
//                case "<" -> input < ruleValue;
//                case ">=" -> input >= ruleValue;
//                case "<=" -> input <= ruleValue;
//                case "==" -> input == ruleValue;
//                default -> false;
//            };
//
//        } catch (NumberFormatException e) {
//            return false; // fail safely
//        }
//    }
//
//    private void saveLog(
//            Map<String, Object> inputData,
//            String decision) {
//
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//
//            DecisionLog log = new DecisionLog();
//            log.setInputData(mapper.writeValueAsString(inputData));
//            log.setDecision(decision);
//
//            logRepository.save(log);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//


//package com.example.policyenginebackend.service;
//
//import com.example.policyenginebackend.model.DecisionLog;
//import com.example.policyenginebackend.model.Rule;
//import com.example.policyenginebackend.model.RuleCondition;
//import com.example.policyenginebackend.repository.DecisionLogRepository;
//import com.example.policyenginebackend.repository.RuleConditionRepository;
//import com.example.policyenginebackend.repository.RuleRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class RuleEngineService {
//
//    private final RuleRepository ruleRepository;
//    private final RuleConditionRepository conditionRepository;
//    private final DecisionLogRepository logRepository;
//    private final ObjectMapper mapper;
//
//    public RuleEngineService(
//            RuleRepository ruleRepository,
//            RuleConditionRepository conditionRepository,
//            DecisionLogRepository logRepository,
//            ObjectMapper mapper) {
//        this.ruleRepository = ruleRepository;
//        this.conditionRepository = conditionRepository;
//        this.logRepository = logRepository;
//        this.mapper = mapper;
//    }
//
//    public String evaluate(Map<String, Object> inputData) {
//
//        List<Rule> rules =
//                ruleRepository.findByActiveTrueOrderByPriorityDesc();
//
//        for (Rule rule : rules) {
//
//            List<RuleCondition> conditions =
//                    conditionRepository.findByRuleId(rule.getId());
//
//            boolean matched = true;
//
//            for (RuleCondition condition : conditions) {
//
//                Object value =
//                        inputData.get(condition.getFieldName());
//
//                if (value == null ||
//                        !compare(
//                                value.toString(),
//                                condition.getOperator(),
//                                condition.getFieldValue())) {
//                    matched = false;
//                    break;
//                }
//            }
//
//            if (matched) {
//                saveLog(inputData, rule.getName());
//                return rule.getName();
//            }
//        }
//
//        saveLog(inputData, "NO_MATCH");
//        return "NO_MATCH";
//    }
//
//    private boolean compare(String input, String op, String expected) {
//        try {
//            double a = Double.parseDouble(input);
//            double b = Double.parseDouble(expected);
//            return switch (op) {
//                case ">" -> a > b;
//                case "<" -> a < b;
//                case ">=" -> a >= b;
//                case "<=" -> a <= b;
//                case "==" -> a == b;
//                default -> false;
//            };
//        } catch (Exception e) {
//            return input.equals(expected);
//        }
//    }
//
//    private void saveLog(Map<String, Object> input, String decision) {
//        try {
//            DecisionLog log = new DecisionLog();
//            log.setInputData(mapper.writeValueAsString(input));
//            log.setDecision(decision);
//            logRepository.save(log);
//        } catch (Exception ignored) {}
//    }
//}


//package com.example.policyenginebackend.service;
//
//import com.example.policyenginebackend.model.DecisionLog;
//import com.example.policyenginebackend.model.Rule;
//import com.example.policyenginebackend.model.RuleCondition;
//import com.example.policyenginebackend.repository.DecisionLogRepository;
//import com.example.policyenginebackend.repository.RuleRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class RuleEngineService {
//
//    private final RuleRepository ruleRepository;
//    private final DecisionLogRepository logRepository;
//    private final ObjectMapper mapper;
//
//    public RuleEngineService(
//            RuleRepository ruleRepository,
//            DecisionLogRepository logRepository,
//            ObjectMapper mapper
//    ) {
//        this.ruleRepository = ruleRepository;
//        this.logRepository = logRepository;
//        this.mapper = mapper;
//    }
//
//    public String evaluate(Map<String, Object> input) {
//
//        List<Rule> rules = ruleRepository
//                .findAllByActiveTrueOrderByPriorityDesc();
//
//        for (Rule rule : rules) {
//
//            List<RuleCondition> conditions = rule.getConditions();
//
//            // Skip rules with no conditions
//            if (conditions == null || conditions.isEmpty()) {
//                continue;
//            }
//
//            boolean allMatched = true;
//
//            for (RuleCondition condition : conditions) {
//
//                if (condition.getFieldName() == null ||
//                        condition.getOperator() == null ||
//                        condition.getFieldValue() == null) {
//                    allMatched = false;
//                    break;
//                }
//
//                Object inputValue = input.get(condition.getFieldName());
//                if (inputValue == null) {
//                    allMatched = false;
//                    break;
//                }
//
//                boolean matched = evaluateCondition(
//                        inputValue.toString(),
//                        condition.getOperator(),
//                        condition.getFieldValue()
//                );
//
//                if (!matched) {
//                    allMatched = false;
//                    break;
//                }
//            }
//
//            if (allMatched) {
//                saveLog(input, rule.getName());
//                return rule.getName();
//            }
//        }
//
//        saveLog(input, "NO_MATCH");
//        return "NO_MATCH";
//    }
//
//    private boolean evaluateCondition(
//            String actualValue,
//            String operator,
//            String expectedValue
//    ) {
//        double actual = Double.parseDouble(actualValue);
//        double expected = Double.parseDouble(expectedValue);
//
//        return switch (operator) {
//            case ">=" -> actual >= expected;
//            case "<=" -> actual <= expected;
//            case ">" -> actual > expected;
//            case "<" -> actual < expected;
//            case "equals", "==" -> actual == expected;
//            default -> false;
//        };
//    }
//
//    private void saveLog(Map<String, Object> input, String decision) {
//        try {
//            DecisionLog log = new DecisionLog();
//            log.setInputData(mapper.writeValueAsString(input));
//            log.setDecision(decision);
//            logRepository.save(log);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


package com.example.policyenginebackend.service;

import com.example.policyenginebackend.model.DecisionLog;
import com.example.policyenginebackend.model.Rule;

import com.example.policyenginebackend.model.RuleCondition;
import com.example.policyenginebackend.repository.DecisionLogRepository;
import com.example.policyenginebackend.repository.RuleConditionRepository;
import com.example.policyenginebackend.repository.RuleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RuleEngineService {

    private final RuleRepository ruleRepository;
    private final DecisionLogRepository logRepository;
    private final RuleConditionRepository ruleConditionRepository;
    private final ObjectMapper mapper;

    public RuleEngineService(
            RuleRepository ruleRepository,
            DecisionLogRepository logRepository,
            RuleConditionRepository ruleConditionRepository,
            ObjectMapper mapper
    ) {
        this.ruleRepository = ruleRepository;
        this.logRepository = logRepository;
        this.ruleConditionRepository = ruleConditionRepository;
        this.mapper = mapper;
    }

    /* =========================
       MAIN RULE EVALUATION
    ========================== */
    public String evaluate(Map<String, Object> input) {

        long startTime = System.currentTimeMillis();

        List<Rule> rules = ruleRepository.findAllByActiveTrueOrderByPriorityDesc();

        for (Rule rule : rules) {

            List<RuleCondition> conditions = rule.getConditions();
            if (conditions == null || conditions.isEmpty()) continue;

            boolean allMatched = true;

            for (RuleCondition condition : conditions) {

                Object inputValue = input.get(condition.getFieldName());
                if (inputValue == null) {
                    allMatched = false;
                    break;
                }

                boolean matched = evaluateCondition(
                        inputValue.toString(),
                        condition.getOperator(),
                        condition.getFieldValue()
                );

                if (!matched) {
                    allMatched = false;
                    break;
                }
            }

            if (allMatched) {
                saveLog(input, rule.getName(), true,
                        System.currentTimeMillis() - startTime);
                return rule.getName();
            }
        }

        saveLog(input, "NO_MATCH", false,
                System.currentTimeMillis() - startTime);
        return "NO_MATCH";
    }


    /* =========================
       OPERATOR NORMALIZATION
       (FIXES YOUR ISSUE 🔥)
    ========================== */
    private String normalizeOperator(String operator) {
        if (operator == null) return null;

        return switch (operator.toLowerCase()) {
            case "greater_than", "gt", ">" -> ">";
            case "less_than", "lt", "<" -> "<";
            case "greater_than_equal", "gte", ">=" -> ">=";
            case "less_than_equal", "lte", "<=" -> "<=";
            case "equals", "equal", "==" -> "==";
            default -> operator; // fallback safety
        };
    }

    /* =========================
       CONDITION EVALUATION
    ========================== */
    private boolean evaluateCondition(
            String actualValue,
            String operator,
            String expectedValue
    ) {
        double actual = Double.parseDouble(actualValue);
        double expected = Double.parseDouble(expectedValue);

        return switch (operator) {
            case ">=" -> actual >= expected;
            case "<=" -> actual <= expected;
            case ">" -> actual > expected;
            case "<" -> actual < expected;
            case "==" -> actual == expected;
            default -> false;
        };
    }
    public void deleteCondition(Long conditionId) {
        if (!ruleConditionRepository.existsById(conditionId)) {
            throw new RuntimeException("Condition not found");
        }
        ruleConditionRepository.deleteById(conditionId);
    }
    /* =========================
       DECISION LOGGING
    ========================== */
    private void saveLog(
            Map<String, Object> input,
            String decision,
            boolean matched,
            long executionTime
    ) {
        try {
            DecisionLog log = new DecisionLog();
            log.setInputData(mapper.writeValueAsString(input));
            log.setDecision(decision);
            log.setMatched(matched);
            log.setExecutionTime(executionTime);
            logRepository.save(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



