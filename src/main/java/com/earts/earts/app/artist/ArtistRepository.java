package com.earts.earts.app.artist;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.earts.earts.entity.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM artist WHERE email = ?1")
	Optional<Artist> getArtistByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM artist WHERE username = ?1")
	Optional<Artist> getArtistByUsername(String username);
}
