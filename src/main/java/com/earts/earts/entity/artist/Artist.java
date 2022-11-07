package com.earts.earts.entity.artist;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.earts.earts.entity.Human;
import com.earts.earts.entity.Role;
import com.earts.earts.entity.artwork.Artwork;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"artworks"})
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artist")
    @JsonIgnore
    private List<Artwork> artworks;

    private Role role;
}
