package com.earts.earts.dto.artwork;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.earts.earts.entity.artwork.ArtworkCategory;
import com.earts.earts.entity.artwork.ArtworkVariation;
import com.earts.earts.entity.artwork.MainColor;

import lombok.Data;

@Data
public class ArtworkDto {

    @NotNull
    @NotBlank
    private String artworkName;

    @NotNull
    @NotBlank
    private String artworkDescription;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ArtworkCategory category;

    @NotNull
    private List<ArtworkVariation> variation;

    @NotNull
    private Double weight;

    @NotNull
    private Double width;

    @NotNull
    private Double length;

    @NotNull
    private Double height;

    @NotNull
    private List<MainColor> listColor;
}
