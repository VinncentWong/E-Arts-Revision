package com.earts.earts.artist;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.isA;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

import com.earts.earts.app.artist.ArtistRepository;
import com.earts.earts.app.artist.ArtistService;
import com.earts.earts.dto.AfterLoginDto;
import com.earts.earts.dto.LoginDto;
import com.earts.earts.dto.RegistrationDto;
import com.earts.earts.entity.Artist;
import com.earts.earts.entity.Response;
import com.earts.earts.entity.Role;
import com.earts.earts.exception.ArtistNotFoundException;
import com.earts.earts.exception.NotAuthenticatedException;
import com.earts.earts.util.IJwt;
import com.earts.earts.util.JwtUtil;
import com.earts.earts.util.ResponseUtil;

@SuppressWarnings("unchecked")
public class ArtistServiceTest {

    private BCryptPasswordEncoder bcrypt;

    private ResponseUtil util;
	
	private ArtistRepository artistRepo;
	
	private JwtUtil<Artist> jwtUtil;

    private ArtistService artistService;

    // test postfixture
    @BeforeEach
    public void setUp(){
        this.bcrypt = mock(BCryptPasswordEncoder.class);
        this.util = mock(ResponseUtil.class);
        this.artistRepo = mock(ArtistRepository.class);
        this.jwtUtil = mock(JwtUtil.class);
        this.artistService = new ArtistService(bcrypt, util, artistRepo, jwtUtil);
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
        when(this.util.sendCreated(eq(MESSAGE), eq(SUCCESS), any(Artist.class)))
        .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(responseObject));

        // act
        ResponseEntity<Response> response = this.artistService.createArtist(dto);
        System.out.println(response);
        // assertion
        assertThat(response) // actual
        .as("artist harus dibuat")
        // expected
        .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(responseObject));
    }

    @Test
    public void artistShouldSuccessLogin() throws ArtistNotFoundException, NotAuthenticatedException{

        // arrange
        LoginDto dto = mock(LoginDto.class);
        Artist artist = mock(Artist.class);
        Response responseObject = new Response();
        responseObject.setData(artist);
        responseObject.setMessage("user authenticated");
        responseObject.setSuccess(true);

        // assumption
        when(dto.getEmailOrUsername()).thenReturn("artist@gmail.com");
        when(this.artistRepo.getArtistByEmail(dto.getEmailOrUsername())).thenReturn(Optional.of(artist));
        when(bcrypt.matches(dto.getPassword(), artist.getPassword())).thenReturn(true);
        when(this.jwtUtil.generateToken(this.jwtUtil::implementationGenerateToken, artist)).thenReturn("jwts_token");
        when(this.util.sendOk(eq("user authenticated"), eq(true), any(Map.class)))
        .thenReturn(ResponseEntity.status(HttpStatus.OK).body(responseObject));

        // act
        var response = this.artistService.loginArtist(dto);
        
        // assertion
        assertThat(response.getBody())
        .as("response login harus sukses login")
        .isEqualTo(responseObject);

        verify(this.util).sendOk(eq("user authenticated"), eq(true), any(Map.class));
        verify(this.artistRepo).getArtistByEmail(dto.getEmailOrUsername());
        verify(this.jwtUtil).generateToken(isA(IJwt.class), eq(artist));
    }

    @Test
    public void artistShouldSuccessAddData() throws ArtistNotFoundException{
        AfterLoginDto afterLoginDto = new AfterLoginDto();
        afterLoginDto.setCity("kota");
        afterLoginDto.setFirstName("budi");
        afterLoginDto.setLastName("last");
        afterLoginDto.setProvince("jawa timur");
        final Long CLIENT_ID = 2L;
        
        Artist artist = new Artist();
        when(this.artistRepo.findById(CLIENT_ID)).thenReturn(Optional.of(artist));

        // init argument captor
        ArgumentCaptor<Artist> captor = ArgumentCaptor.forClass(Artist.class);
        ArgumentCaptor<String> captorString = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Boolean> captorBoolean = ArgumentCaptor.forClass(Boolean.class);

        this.artistService.addArtistData(afterLoginDto, CLIENT_ID);

        verify(this.util).sendOk(captorString.capture(), captorBoolean.capture(), captor.capture());
        assertThat(captorString.getValue()).isEqualTo("sukses menambahkan data artist");
        assertThat(captorBoolean.getValue()).isEqualTo(true);
        assertThat(captor.getValue().getCity()).isEqualTo("kota");
        assertThat(captor.getValue().getFirstName()).isEqualTo("budi");
        assertThat(captor.getValue().getLastName()).isEqualTo("last");
        assertThat(captor.getValue().getProvince()).isEqualTo("jawa timur");
    }
}
