package com.earts.earts.app.artwork;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.earts.earts.entity.artwork.Artwork;

public interface ArtworkRepository extends CrudRepository<Artwork, String>{

    @Query(nativeQuery = true, value = "SELECT * FROM artwork WHERE artist_id = ?1")
    Optional<Artwork> getArtworkByArtistId(Long artistId);
}
