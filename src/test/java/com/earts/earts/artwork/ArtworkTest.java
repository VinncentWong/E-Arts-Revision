package com.earts.earts.artwork;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import com.earts.earts.app.artist.ArtistRepository;
import com.earts.earts.app.artwork.ArtworkRepository;
import com.earts.earts.app.artwork.ArtworkService;
import com.earts.earts.app.artwork.artwork_photo.ArtworkPhotoRepository;
import com.earts.earts.dto.artwork.ArtworkDto;
import com.earts.earts.entity.artist.Artist;
import com.earts.earts.entity.artwork.Artwork;
import com.earts.earts.entity.artwork.ArtworkCategory;
import com.earts.earts.exception.ArtistNotFoundException;
import com.earts.earts.util.ResponseUtil;

public class ArtworkTest {

    private ArtistRepository artistRepository;

    private ArtworkRepository artworkRepository;

    private ArgumentCaptor<String> captorMessage;

    private ArgumentCaptor<Object> captorData;
    
    private ArgumentCaptor<Boolean> captorSuccess;

    private ResponseUtil responseUtil;

    private ArtworkService service;

    private ArtworkPhotoRepository photoRepository;

    // test fixture
    @BeforeEach
    public void setUp(){
        this.artistRepository = mock(ArtistRepository.class);
        this.artworkRepository = mock(ArtworkRepository.class);
        this.responseUtil = mock(ResponseUtil.class);
        this.captorData = ArgumentCaptor.forClass(Object.class);
        this.captorMessage = ArgumentCaptor.forClass(String.class);
        this.captorSuccess = ArgumentCaptor.forClass(Boolean.class);
        this.photoRepository = mock(ArtworkPhotoRepository.class);
        this.service = new ArtworkService(this.artworkRepository, this.artistRepository, this.responseUtil, this.photoRepository);
    }

    @Test
    @DisplayName("artwork harus terbuat")
    public void artworkShouldBeCreated() throws ArtistNotFoundException, IOException{
        List<MultipartFile> files = new ArrayList<>();
        files.add(new MultipartFile(){

            @Override
            public String getName() {
                return "file";
            }

            @Override
            @Nullable
            public String getOriginalFilename() {
                return "originalfilename";
            }

            @Override
            @Nullable
            public String getContentType() {
                return "content_type";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[]{1};
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                // empty implementation just for checking
            }

        });
        ArtworkDto dto = new ArtworkDto();
        dto.setArtworkDescription("description1");
        dto.setArtworkName("artwork1");
        dto.setCategory(ArtworkCategory.ALL);
        dto.setHeight(10D);
        dto.setLength(10D);
        dto.setWeight(10D);
        dto.setWidth(10D);
        Artist artist = new Artist();
        artist.setUsername("artist");
        when(this.artistRepository.findById(eq(1L))).thenReturn(Optional.of(artist));

        this.service.createArtwork(1L, dto, files);

        verify(this.responseUtil).sendCreated(captorMessage.capture(), captorSuccess.capture(), captorData.capture());
        assertThat(captorMessage.getValue()).isEqualTo("sukses membuat artwork");
        assertThat(captorSuccess.getValue()).isEqualTo(true);
        Artwork assertArtwork = (Artwork)this.captorData.getValue();
        assertThat(assertArtwork.getArtist().getUsername()).isEqualTo("artist");
        assertThat(assertArtwork.getDescription()).isEqualTo("description1");
        assertThat(assertArtwork.getArtworkName()).isEqualTo("artwork1");
        assertThat(assertArtwork.getCategory().name()).isEqualTo(ArtworkCategory.ALL.name());
        assertThat(assertArtwork.getHeight()).isEqualTo(10);
        assertThat(assertArtwork.getLength()).isEqualTo(10);
        assertThat(assertArtwork.getWeight()).isEqualTo(10);
        assertThat(assertArtwork.getWidth()).isEqualTo(10);
    }
}
