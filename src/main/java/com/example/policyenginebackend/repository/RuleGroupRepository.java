package com.example.policyenginebackend.repository;

import com.example.policyenginebackend.model.RuleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleGroupRepository extends JpaRepository<RuleGroup, Long> {
}
