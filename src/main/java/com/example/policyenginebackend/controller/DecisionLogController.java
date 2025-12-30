//package com.example.policyenginebackend.controller;
//
//import com.example.policyenginebackend.model.DecisionLog;
//import com.example.policyenginebackend.repository.DecisionLogRepository;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

//@RestController
//@RequestMapping("/api/logs")
//@CrossOrigin(origins = "*")
//public class DecisionLogController {
//
//    private final DecisionLogRepository decisionLogRepository;
//
//    public DecisionLogController(DecisionLogRepository decisionLogRepository) {
//        this.decisionLogRepository = decisionLogRepository;
//    }
//
//    // 🔹 Used by "Decision Logs" tab in UI
//    @GetMapping
//    public List<DecisionLog> getAllLogs() {
//        return decisionLogRepository.findAll();
//    }
//}


package com.example.policyenginebackend.controller;

import com.example.policyenginebackend.model.DecisionLog;
import com.example.policyenginebackend.repository.DecisionLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decision-logs")

public class DecisionLogController {

    private final DecisionLogRepository decisionLogRepository;

    public DecisionLogController(DecisionLogRepository decisionLogRepository) {
        this.decisionLogRepository = decisionLogRepository;
    }

    // Admin / global: used by admin Decision Logs + Analytics
    @GetMapping
    public List<DecisionLog> getAllLogs(
            @RequestParam(defaultValue = "50") int limit
    ) {
        // simplest version: just return all logs, ignore limit & sorting for now
        return decisionLogRepository.findAll();
    }

    // User-only logs: for now same as global
    @GetMapping("/my")
    public List<DecisionLog> getMyLogs(
            @RequestParam(defaultValue = "50") int limit
    ) {
        return decisionLogRepository.findAll();
    }
}

