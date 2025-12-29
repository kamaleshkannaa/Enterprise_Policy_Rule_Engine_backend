//package com.example.policyenginebackend.model;
//
//import jakarta.persistence.*;

//@Entity
//@Table(name = "rule_condition")
//public class RuleCondition {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "field_name")
//    private String fieldName;
//
//    private String operator;
//
//    @Column(name = "field_value")
//    private String fieldValue;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "rule_id", nullable = false)
//    private Rule rule;
//
//    // ===== GETTERS =====
//    public Long getId() { return id; }
//    public String getFieldName() { return fieldName; }
//    public String getOperator() { return operator; }
//    public String getFieldValue() { return fieldValue; }
//    public Rule getRule() { return rule; }
//
//    // ===== SETTERS =====
//    public void setId(Long id) { this.id = id; }
//    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
//    public void setOperator(String operator) { this.operator = operator; }
//    public void setFieldValue(String fieldValue) { this.fieldValue = fieldValue; }
//
//    // 🔥 THIS METHOD FIXES YOUR ERROR
//    public void setRule(Rule rule) {
//        this.rule = rule;
//    }
//}

package com.example.policyenginebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "rule_conditions")
public class RuleCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;
    private String operator;
    private String fieldValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", nullable = false)
    @JsonIgnore
    @JsonIgnoreProperties("conditions")// 🔥 THIS FIXES JACKSON ERROR
    private Rule rule;

    public Long getId() { return id; }
    public String getFieldName() { return fieldName; }
    public String getOperator() { return operator; }
    public String getFieldValue() { return fieldValue; }
    public Rule getRule() { return rule; }

    public void setId(Long id) { this.id = id; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
    public void setOperator(String operator) { this.operator = operator; }
    public void setFieldValue(String fieldValue) { this.fieldValue = fieldValue; }
    public void setRule(Rule rule) { this.rule = rule; }
}

