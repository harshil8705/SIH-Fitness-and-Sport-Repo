package com.ai_llm.valetude.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {

    @NotBlank
    private String exerciseName;

    @NotBlank
    private String exerciseDescription;

    @NotBlank
    private String exerciseSteps;

    @NotBlank
    private String exerciseBenefits;

}
