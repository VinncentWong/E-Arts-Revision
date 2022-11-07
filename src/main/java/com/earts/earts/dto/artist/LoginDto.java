package com.earts.earts.dto.artist;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDto {
    
    @NotBlank(message = "email atau username tidak boleh kosong")
    @NotNull(message = "email atau username tidak boleh kosong")
    private String emailOrUsername;

    @NotBlank(message = "password tidak boleh kosong")
    @NotNull(message = "password tidak boleh kosong")
    private String password;
}
