package com.earts.earts.artist;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

import com.earts.earts.app.artist.ArtistRepository;
import com.earts.earts.app.artist.ArtistService;
import com.earts.earts.dto.RegistrationDto;
import com.earts.earts.entity.Artist;
import com.earts.earts.entity.Response;
import com.earts.earts.entity.Role;
import com.earts.earts.util.JwtUtil;
import com.earts.earts.util.ResponseUtil;

public class ArtistServiceTest {

    private BCryptPasswordEncoder bcrypt;

    private ResponseUtil util;
	
	private ArtistRepository artistRepo;
	
	private JwtUtil<Artist> jwtUtil;

    @SuppressWarnings("unchecked")
    // test postfixture
    @BeforeEach
    public void setUp(){
        this.bcrypt = mock(BCryptPasswordEncoder.class);
        this.util = mock(ResponseUtil.class);
        this.artistRepo = mock(ArtistRepository.class);
        this.jwtUtil = mock(JwtUtil.class);
    }
    
    // --------------------------------------HAPPY FLOW-----------------------------------------
    @Test
    public void shouldInstantiateAllProperty(){
        //assert
        assertThat(this.bcrypt).isInstanceOf(BCryptPasswordEncoder.class);
        assertThat(this.util).isInstanceOf(ResponseUtil.class);
        assertThat(this.artistRepo).isInstanceOf(ArtistRepository.class);
        assertThat(this.jwtUtil).isInstanceOf(JwtUtil.class);
    }

    @Test
    public void artistShouldBeCreated(){
        RegistrationDto dto = mock(RegistrationDto.class);
        Artist artist = new Artist();
        artist.setUsername("artist@gmail.com");
        artist.setEmail("artist");
        artist.setRole(Role.ARTIST);
        artist.setPassword("bcrypt123456");
        ArtistService artistService = new ArtistService(
            this.bcrypt, this.util, this.artistRepo, this.jwtUtil
        );
        final String MESSAGE = "success create user data";
        final boolean SUCCESS = true;
        Response responseObject = new Response();
        responseObject.setData(artist);
        responseObject.setMessage(MESSAGE);
        responseObject.setSuccess(SUCCESS);

        // assumption
        when(dto.getEmail()).thenReturn("artist@gmail.com");
        when(dto.getUsername()).thenReturn("artist");
        when(dto.getPassword()).thenReturn("123456");
        when(dto.getRole()).thenReturn(Role.ARTIST);
        when(this.artistRepo.getArtistByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(this.artistRepo.getArtistByUsername(dto.getUsername())).thenReturn(Optional.empty());
        when(this.bcrypt.encode(dto.getPassword())).thenReturn("bcrypt123456");
        when(this.util.sendCreated(MESSAGE, SUCCESS, artist))
        .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(responseObject));

        // act
        ResponseEntity<Response> response = artistService.createArtist(dto);
        System.out.println(response);
        // assertion
        assertThat(response) // actual
        .as("artist harus dibuat")
        // expected
        .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(responseObject));
    }
}
