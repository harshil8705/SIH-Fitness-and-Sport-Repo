package com.ai_llm.valetude.security.dto;

import com.ai_llm.valetude.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

    private String fullName;
    private String height;
    private String weight;
    private Gender gender;
    private String fitnessGoal;

}
