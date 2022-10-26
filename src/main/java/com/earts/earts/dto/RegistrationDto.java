package com.earts.earts.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.earts.earts.entity.Role;

import lombok.Data;

@Data
public class RegistrationDto {
    
    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;
}
