package com.earts.earts.app.artwork;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.earts.earts.entity.artwork.Artwork;

public interface ArtworkRepository extends CrudRepository<Artwork, String>{}
