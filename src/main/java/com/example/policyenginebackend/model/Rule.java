package com.example.policyenginebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int priority;
    private boolean active;

    @Column(name = "group_id")
    private Long groupId;

    // 🔥 CASCADE + ORPHAN REMOVAL
    @OneToMany(
            mappedBy = "rule",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonIgnoreProperties("rule")
    private List<RuleCondition> conditions;

    // getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public boolean isActive() { return active; }
    public Long getGroupId() { return groupId; }
    public List<RuleCondition> getConditions() { return conditions; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setActive(boolean active) { this.active = active; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public void setConditions(List<RuleCondition> conditions) { this.conditions = conditions; }
}
