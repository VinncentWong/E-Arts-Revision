package com.earts.earts.entity.artwork;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.earts.earts.entity.artist.Artist;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artwork")
    private List<ArtworkVariation> variation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artwork")
    private List<ArtworkPhoto> photo;

    private Double weight;

    private Double length;

    private Double width;

    private Double height;

    @ManyToOne(fetch = FetchType.LAZY)
    private Artist artist;
}
