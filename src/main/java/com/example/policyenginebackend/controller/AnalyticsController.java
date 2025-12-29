//package com.example.policyenginebackend.controller;
//
//import com.example.policyenginebackend.repository.DecisionLogRepository;
//import com.example.policyenginebackend.repository.RuleRepository;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/analytics")
//@CrossOrigin("*")
//public class AnalyticsController {
//
//    private final DecisionLogRepository decisionLogRepository;
//    private final RuleRepository ruleRepository;
//
//    public AnalyticsController(
//            DecisionLogRepository decisionLogRepository,
//            RuleRepository ruleRepository
//    ) {
//        this.decisionLogRepository = decisionLogRepository;
//        this.ruleRepository = ruleRepository;
//    }
//
//    @GetMapping
//    public Map<String, Object> getAnalytics() {
//
//        long totalDecisions = decisionLogRepository.count();
//        long matchedDecisions =
//                decisionLogRepository.countByDecisionNot("NO_MATCH");
//
//        long totalRules = ruleRepository.count();
//        long activeRules = ruleRepository.countByActiveTrue();
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("totalDecisions", totalDecisions);
//        result.put("matchedDecisions", matchedDecisions);
//        result.put(
//                "matchRate",
//                totalDecisions == 0 ? 0 :
//                        (matchedDecisions * 100.0) / totalDecisions
//        );
//        result.put("totalRules", totalRules);
//        result.put("activeRules", activeRules);
//
//        return result;
//    }
//}


package com.example.policyenginebackend.controller;

import com.example.policyenginebackend.repository.DecisionLogRepository;
import com.example.policyenginebackend.repository.RuleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin("*")
public class AnalyticsController {

    private final DecisionLogRepository decisionLogRepository;
    private final RuleRepository ruleRepository;

    public AnalyticsController(
            DecisionLogRepository decisionLogRepository,
            RuleRepository ruleRepository
    ) {
        this.decisionLogRepository = decisionLogRepository;
        this.ruleRepository = ruleRepository;
    }

    @GetMapping
    public Map<String, Object> getAnalytics() {

        long totalDecisions = decisionLogRepository.count();
        long matchedDecisions =
                decisionLogRepository.countByDecisionNot("NO_MATCH");

        long totalRules = ruleRepository.count();
        long activeRules = ruleRepository.countByActiveTrue();

        Map<String, Object> result = new HashMap<>();
        result.put("totalDecisions", totalDecisions);
        result.put("matchedDecisions", matchedDecisions);
        result.put(
                "matchRate",
                totalDecisions == 0 ? 0 :
                        (matchedDecisions * 100.0) / totalDecisions
        );
        result.put("totalRules", totalRules);
        result.put("activeRules", activeRules);

        // avgExecutionTime + decisionsToday were computed on frontend using logs;
        // keeping them 0 here is fine, frontend will fill from logs if needed.
        return result;
    }
}


