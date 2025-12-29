package com.example.policyenginebackend.repository;

import com.example.policyenginebackend.model.RuleCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RuleConditionRepository
        extends JpaRepository<RuleCondition, Long> {

    // ✅ REQUIRED for engine + controller
    List<RuleCondition> findByRuleId(Long ruleId);

    // ✅ REQUIRED for safe rule delete
    @Modifying
    @Transactional
    @Query("DELETE FROM RuleCondition rc WHERE rc.rule.id = :ruleId")
    void deleteByRuleId(@Param("ruleId") Long ruleId);
}
