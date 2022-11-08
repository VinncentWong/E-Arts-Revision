package com.earts.earts.app.artwork;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.earts.earts.app.artist.ArtistRepository;
import com.earts.earts.app.artwork.artwork_photo.ArtworkPhotoRepository;
import com.earts.earts.dto.artwork.ArtworkDto;
import com.earts.earts.entity.Response;
import com.earts.earts.entity.artist.Artist;
import com.earts.earts.entity.artwork.Artwork;
import com.earts.earts.entity.artwork.ArtworkPhoto;
import com.earts.earts.exception.ArtistNotFoundException;
import com.earts.earts.exception.ArtworkNotFoundException;
import com.earts.earts.util.ResponseUtil;

@Service
public class ArtworkService {

    private final ArtworkRepository artworkRepository;

    private final ArtistRepository artistRepository;

    private final ArtworkPhotoRepository artworkPhotoRepository;
    
    private final ResponseUtil util;

    private boolean savePhotoSuccess;
    
    @Autowired
    public ArtworkService(
    ArtworkRepository repository, 
    ArtistRepository artistRepository, 
    ResponseUtil util,
    ArtworkPhotoRepository photoRepository){
        this.artworkRepository = repository;
        this.artistRepository = artistRepository;
        this.util = util;
        this.artworkPhotoRepository = photoRepository;
    }

    public ResponseEntity<Response> createArtwork(Long artistId, ArtworkDto artworkDto, List<MultipartFile> files) throws ArtistNotFoundException, IOException{
        Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
        Artwork artwork = new Artwork();
        artwork.setArtist(artist);
        artwork.setArtworkName(artworkDto.getArtworkName());
        artwork.setCategory(artworkDto.getCategory());
        artwork.setDescription(artworkDto.getArtworkDescription());
        artwork.setHeight(artworkDto.getHeight());
        artwork.setLength(artworkDto.getLength());
        this.savePhotoSuccess = true;
        files.forEach((x) -> {
            try{
                ArtworkPhoto artworkPhoto = new ArtworkPhoto();
                artworkPhoto.setArtwork(artwork);
                artworkPhoto.setPhoto(x.getBytes());
                this.artworkPhotoRepository.save(artworkPhoto);
            }
            catch(IOException exception){
                savePhotoSuccess = false;
            }
        });
        if(!this.savePhotoSuccess){
            return this.util.sendInternalServerError("kesalahan internal sistem", false);
        }
        artwork.setVariation(artworkDto.getVariation());
        artwork.setWeight(artworkDto.getWeight());
        artwork.setWidth(artworkDto.getWidth());
        this.artworkRepository.save(artwork);
        return this.util.sendCreated("sukses membuat artwork", true, artwork);
    }

    public ResponseEntity<Response> getArtwork(String uuid) throws ArtworkNotFoundException{
        Artwork artwork = this.artworkRepository.findById(uuid).orElseThrow(() -> new ArtworkNotFoundException("artwork tidak ditemukan"));
        return this.util.sendOk("sukses mendapatkan artwork", true, artwork);
    }

    public ResponseEntity<Response> getArtworks(){
        List<Artwork> list = (List<Artwork>)this.artworkRepository.findAll();
        return this.util.sendOk("sukses mendapatkan semua artwork", true, list);
    }

    public ResponseEntity<Response> getArtwork(Long artistId) throws ArtworkNotFoundException{
        Artwork artwork = this.artworkRepository.getArtworkByArtistId(artistId).orElseThrow(() -> new ArtworkNotFoundException("artwork tidak ditemukan"));
        return this.util.sendOk("sukses mendapatkan artwork", true, artwork);
    }
}
