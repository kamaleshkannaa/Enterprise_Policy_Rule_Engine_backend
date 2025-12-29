package com.example.policyenginebackend.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
public class DecisionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "json")
    private String inputData;

    private String decision;

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean matched;

    private long executionTime; // ms
}


