package com.ai_llm.valetude.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    private String username;
    private String email;
    private String mobileNo;
    private String password;

}
