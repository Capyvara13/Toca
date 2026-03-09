package com.atdo.toca_cms.application.dto.artist;

import com.atdo.toca_cms.domain.entity.common.CastAndCrew;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistResponseDto {
    /**
     * The unique identifier of the artist.
     */
    private Long idArtist;
    /**
     * The URL-friendly identifier for the artist.
     */
    private String slug;
    /**
     * The official name of the artist.
     */
    private String name;
    /**
     * The artist's date of birth.
     */
    private LocalDate birthDate;
    /**
     * The artist's date of death (if applicable).
     */
    private LocalDate deathDate;
    /**
     * A detailed biography of the artist.
     */
    private String bio;
    /**
     * URL for the artist's profile photo.
     */
    private String photoUrl;
    /**
     * The instant when the artist record was created.
     */
    private Instant createdAt;
    /**
     * The instant when the artist record was last updated.
     */
    private Instant updatedAt;

    public ArtistResponseDto(long idArtist, @NonNull String slug, @NonNull String name, String bio, String photoUrl, List<CastAndCrew> castAndCrew, LocalDate birthDate, LocalDate deathDate, @NonNull Instant createdAt, Instant updatedAt) {
    }
}
