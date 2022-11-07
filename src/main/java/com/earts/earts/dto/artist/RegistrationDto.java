package com.earts.earts.dto.artist;

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
    
    @NotNull(message = "email tidak boleh kosong")
    @NotBlank(message = "email tidak boleh kosong")
    @Email(message = "format email tidak valid")
    private String email;

    @NotNull(message = "username tidak boleh kosong")
    @NotBlank(message = "username tidak boleh kosong")
    private String username;

    @NotNull(message = "password tidak boleh kosong")
    @NotBlank(message = "password tidak boleh kosong")
    private String password;

    @NotNull(message = "birth date tidak boleh kosong")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "role tidak boleh kosong")
    private Role role;
}
