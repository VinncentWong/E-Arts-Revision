package com.earts.earts.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Artwork {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID uuid;
    
    private String artworkName;

    private String description;

    private ArtworkCategory category;

    private Integer stock;

    private List<String> variation;
    
    @ElementCollection
    @CollectionTable(
        name = "artwork_photo",
        joinColumns = {@JoinColumn(name = "artwork_id")}
    )
    private List<byte[]> photo;

    private Integer weight;

    private Integer length;

    private Integer width;

    private Integer height;

    private Boolean isPreorder;
}
