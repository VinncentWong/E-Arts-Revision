package com.earts.earts.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AfterLoginDto {
    
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;
    
    @NotNull
    @NotBlank
    private String province;

    @NotNull
    @NotBlank
    private String city;
}
