package com.earts.earts.app.artist;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.earts.earts.dto.AfterLoginDto;
import com.earts.earts.dto.LoginDto;
import com.earts.earts.dto.RegistrationDto;
import com.earts.earts.entity.Artist;
import com.earts.earts.entity.Response;
import com.earts.earts.exception.ArtistNotFoundException;
import com.earts.earts.exception.NotAuthenticatedException;
import com.earts.earts.util.JwtUtil;
import com.earts.earts.util.ResponseUtil;

@Service
public class ArtistService {
    
    private final BCryptPasswordEncoder bcrypt;
	
	private final ResponseUtil util;
	
	private final ArtistRepository artistRepo;
	
	private final JwtUtil<Artist> jwtUtil;
	
	@Autowired
	public ArtistService(BCryptPasswordEncoder bcrypt, 
			ResponseUtil util, ArtistRepository artistRepo, JwtUtil<Artist> jwtUtil) {
		this.bcrypt = bcrypt;
		this.util = util;
		this.artistRepo = artistRepo;
		this.jwtUtil = jwtUtil;
	}
	
	public ResponseEntity<Response> createArtist(RegistrationDto dto){
		Optional<Artist> tempArtist = this.artistRepo.getArtistByEmail(dto.getEmail());
        Optional<Artist> tempArtist2 = this.artistRepo.getArtistByUsername(dto.getUsername());
		if(tempArtist.isPresent() || tempArtist2.isPresent()) {
			return this.util.sendBadRequest("email sudah terdaftar", false);
		}
		Artist artist = new Artist();
		artist.setEmail(dto.getEmail());
		artist.setUsername(dto.getUsername());
		artist.setPassword(bcrypt.encode(dto.getPassword()));
		artist.setRole(dto.getRole());
		this.artistRepo.save(artist);
		return util.sendCreated("success create user data", true, artist);
	}
	
	public ResponseEntity<Response> loginArtist(LoginDto dto) throws ArtistNotFoundException, NotAuthenticatedException{
		Optional<Artist> artistByEmail = artistRepo.getArtistByEmail(dto.getEmailOrUsername());
        if(artistByEmail.isEmpty()){
            artistByEmail = artistRepo.getArtistByUsername(dto.getEmailOrUsername());
            if(artistByEmail.isEmpty()){
                throw new ArtistNotFoundException();
            }
        }
        Artist artist = artistByEmail.get();
		if(bcrypt.matches(dto.getPassword(), artist.getPassword())) {
			String token = jwtUtil.generateToken(jwtUtil::implementationGenerateToken, artist);
			Map<String, Object> map = new HashMap<>();
			map.put("data", artist);
			map.put("jwt_token", token);
			return util.sendOk("user authenticated", true, map);
		} else {
			throw new NotAuthenticatedException();
		}
	}

	public ResponseEntity<Response> addArtistData(AfterLoginDto dto, Long clientId) throws ArtistNotFoundException{
		Artist artist = artistRepo.findById(clientId).orElseThrow(() -> new ArtistNotFoundException());
		artist.setFirstName(dto.getFirstName());
		artist.setLastName(dto.getLastName());
		artist.setProvince(dto.getProvince());
		artist.setCity(dto.getCity());
		this.artistRepo.save(artist);
		return this.util.sendOk("sukses menambahkan data artist", true, artist);
	}

	// public ResponseEntity<Response> updateArtist(Long artistId, UpdateArtistDto dto) throws ArtistNotFoundException{
	// 	Artist artist = artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
	// 	SetterNullAware setter = new SetterNullAware();
	// 	if(bcrypt.matches(dto.getOldPassword(), artist.getPassword())){
	// 		setter.setString(artist::setEmail, dto.getEmail());
	// 		setter.setString(artist::setPassword, dto.getNewPassword());
	// 		setter.setString(artist::setUsername, dto.getUsername());
	// 		return this.util.sendOk("sukses mengupdate data artist", true, artist);
	// 	}
	// 	return this.util.sendInternalServerError("terjadi kesalahan pada saat mengupdate data artist", false);
	// }
	
	public ResponseEntity<Response> getArtistById(Long id) throws ArtistNotFoundException{
		Artist artist = artistRepo.findById(id).orElseThrow(() -> new ArtistNotFoundException());
		return util.sendOk("data artis ditemukan", true, artist);
	}
	
	public ResponseEntity<Response> deleteArtist(Long id) throws ArtistNotFoundException{
		this.artistRepo.findById(id).orElseThrow(() -> new ArtistNotFoundException());
		this.artistRepo.deleteById(id);
		return this.util.sendOk("sukses menghapus data artist", true, null);
	}

    public ResponseEntity<Response> getAllArtist() throws ArtistNotFoundException{
		Iterable<Artist> artist = artistRepo.findAll();
		return util.sendOk("data artis ditemukan", true, artist);
	}
}
