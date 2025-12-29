package com.example.policyenginebackend.repository;

import com.example.policyenginebackend.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findByActiveTrueOrderByPriorityDesc();
    List<Rule> findAllByActiveTrueOrderByPriorityDesc();
    long countByActiveTrue();
}

