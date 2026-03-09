package com.atdo.toca_cms.adapter.api;

import com.atdo.toca_cms.application.dto.artist.ArtistFilterDto;
import com.atdo.toca_cms.application.dto.artist.ArtistResponseDto;
import com.atdo.toca_cms.application.usecase.ArtistUsecase;
import com.atdo.toca_cms.domain.entity.common.Artist;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistUsecase usecase;

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findById(@PathVariable Long id) {
        Artist artist = usecase.searchByIdOrFail(id);
        return ResponseEntity.ok(artist);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Artist> findBySlug(@PathVariable String slug) {
        Artist artist = usecase.searchBySlugOrFail(slug);
        return ResponseEntity.ok(artist);
    }

    @PostMapping
    public ResponseEntity<Artist> create(@RequestBody @Valid Artist artist) {
        Artist savedArtist = usecase.save(artist);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> update(@PathVariable Long id, @RequestBody @Valid Artist artist) {
        Artist artistWithId = artist.toBuilder().idArtist(id).build();
        Artist updatedArtist = usecase.save(artistWithId);
        return  ResponseEntity.ok(updatedArtist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usecase.delete(id);
    }

    @GetMapping
    public ResponseEntity<Page<ArtistResponseDto>> list(ArtistFilterDto filterDto) {
        Page<Artist> artistPage = usecase.findAll(filterDto);

        Page<ArtistResponseDto> responsePage = artistPage.map(artist -> new ArtistResponseDto(
                artist.getIdArtist(),
                artist.getSlug(),
                artist.getName(),
                artist.getBio(),
                artist.getPhotoUrl(),
                artist.getCastAndCrew(),
                artist.getBirthDate(),
                artist.getDeathDate(),
                artist.getCreatedAt(),
                artist.getUpdatedAt()
        ));

        return ResponseEntity.ok(responsePage);
    }
}