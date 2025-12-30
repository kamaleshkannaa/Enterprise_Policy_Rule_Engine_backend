//package com.example.policyenginebackend.repository;
//
//import com.example.policyenginebackend.model.RuleCondition;
//import org.springframework.data.jpa.repository.*;
//import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//public interface RuleConditionRepository
//        extends JpaRepository<RuleCondition, Long> {
//
//    // ✅ REQUIRED for engine + controller
//    List<RuleCondition> findByRuleId(Long ruleId);
//
//    // ✅ REQUIRED for safe rule delete
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM RuleCondition rc WHERE rc.rule.id = :ruleId")
//    void deleteByRuleId(@Param("ruleId") Long ruleId);
//}
package com.example.policyenginebackend.repository;

import com.example.policyenginebackend.model.RuleCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RuleConditionRepository
        extends JpaRepository<RuleCondition, Long> {

    // ✅ Used for GET /rules/{ruleId}/conditions
    List<RuleCondition> findByRuleId(Long ruleId);

    // ✅ Used when deleting an entire rule
    @Modifying
    @Transactional
    @Query("DELETE FROM RuleCondition rc WHERE rc.rule.id = :ruleId")
    void deleteByRuleId(@Param("ruleId") Long ruleId);

    // ✅ ⭐ THIS FIXES YOUR DELETE CONDITION PROBLEM
    @Modifying
    @Transactional
    @Query("DELETE FROM RuleCondition rc WHERE rc.id = :id AND rc.rule.id = :ruleId")
    int deleteByIdAndRuleId(
            @Param("id") Long id,
            @Param("ruleId") Long ruleId
    );
}
