package com.earts.earts.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDto {
    
    @NotBlank
    @NotNull
    private String emailOrUsername;

    @NotBlank
    @NotNull
    private String password;
}
