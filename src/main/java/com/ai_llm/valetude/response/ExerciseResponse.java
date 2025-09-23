package com.ai_llm.valetude.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponse {

    private Long exerciseId;
    private String exerciseName;
    private String exerciseDescription;
    private String exerciseSteps;
    private String exerciseBenefits;

}
