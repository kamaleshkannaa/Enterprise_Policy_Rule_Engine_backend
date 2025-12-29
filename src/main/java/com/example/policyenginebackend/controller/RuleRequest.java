package com.example.policyenginebackend.controller;

public record RuleRequest(
        String name,
        String description,
        int priority,
        boolean is_active,
        Long rule_group_id
) {}
