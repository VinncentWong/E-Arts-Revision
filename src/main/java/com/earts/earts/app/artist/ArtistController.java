package com.earts.earts.app.artist;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.earts.earts.dto.artist.AfterLoginDto;
import com.earts.earts.dto.artist.LoginDto;
import com.earts.earts.dto.artist.RegistrationDto;
import com.earts.earts.entity.Response;
import com.earts.earts.exception.ArtistNotFoundException;
import com.earts.earts.exception.NotAuthenticatedException;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    
	private final ArtistService service;
	
	@Autowired
	public ArtistController(ArtistService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Response> createArtist(@RequestBody @Valid RegistrationDto dto){
		return service.createArtist(dto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> loginArtist(@RequestBody @Valid LoginDto dto) throws ArtistNotFoundException, NotAuthenticatedException{
		return service.loginArtist(dto);
	}

	@PostMapping("/adddata/{id}")
	public ResponseEntity<Response> addArtistData(@RequestBody @Valid AfterLoginDto dto, @PathVariable("id") Long id) throws ArtistNotFoundException{
		return service.addArtistData(dto, id);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getArtistById(@PathVariable("id") Long id) throws ArtistNotFoundException{
		return service.getArtistById(id);
	}

	@GetMapping("/get")
	public ResponseEntity<Response> getAll() {
		return service.getAllArtist();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteArtist(@PathVariable("id") Long id) throws ArtistNotFoundException{
		return service.deleteArtist(id);
	}
}
