package com.earts.earts.dto.artist;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AfterLoginDto {
    
    @NotNull(message = "firstname tidak boleh kosong")
    @NotBlank(message = "firstname tidak boleh kosong")
    private String firstName;

    @NotNull(message = "lastname tidak boleh kosong")
    @NotBlank(message = "lastname tidak boleh kosong")
    private String lastName;
    
    @NotNull(message = "province tidak boleh kosong")
    @NotBlank(message = "province tidak boleh kosong")
    private String province;

    @NotNull(message = "city tidak boleh kosong")
    @NotBlank(message = "city tidak boleh kosong")
    private String city;
}
