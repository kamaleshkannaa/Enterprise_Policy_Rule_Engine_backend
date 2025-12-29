//package com.example.policyenginebackend.repository;
//
//import com.example.policyenginebackend.model.DecisionLog;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface DecisionLogRepository extends JpaRepository<DecisionLog, Long> {
//    long countByDecisionNot(String decision);
//}


package com.example.policyenginebackend.repository;

import com.example.policyenginebackend.model.DecisionLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DecisionLogRepository extends JpaRepository<DecisionLog, Long> {

    long countByDecisionNot(String decision);
}
