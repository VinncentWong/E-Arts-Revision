package com.earts.earts.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Artist implements Human{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String province;

    private String city;

    private String username;

    private String password;

    private Date birthDate;

    private String description;

    private String instagramLink;

    private String linkedinLink;

    private String facebookLink;

    private String whatsappNumber;

    private Integer rating;

    @ColumnDefault("0")
    private Integer totalArtwork;

    @ColumnDefault("0")
    private Integer totalSales;

    @ColumnDefault("0")
    private Integer totalLike;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    private Boolean isVerified;

    private List<Artwork> artworks;

    private Role role;
}
