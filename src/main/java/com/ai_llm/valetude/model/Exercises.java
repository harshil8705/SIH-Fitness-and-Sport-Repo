package com.ai_llm.valetude.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    @Column(unique = true, nullable = false)
    private String exerciseName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exerciseDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exerciseSteps;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exerciseBenefits;

}
