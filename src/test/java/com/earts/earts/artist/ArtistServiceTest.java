package com.earts.earts.artist;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

import com.earts.earts.app.artist.ArtistRepository;
import com.earts.earts.entity.Artist;
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
    
    @Test
    public void shouldInstantiateAllProperty(){
        
        //assert
        assertThat(this.bcrypt).isInstanceOf(BCryptPasswordEncoder.class);
        assertThat(this.util).isInstanceOf(ResponseUtil.class);
        assertThat(this.artistRepo).isInstanceOf(ArtistRepository.class);
        assertThat(this.jwtUtil).isInstanceOf(JwtUtil.class);
    }
}
