package com.ai_llm.valetude.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    private Integer currentWeight;

    @NotNull
    private Integer height;

    @NotNull
    private Integer goalWeight;

    @NotNull
    private String fitnessGoal;

}
