package com.atdo.toca_cms.domain.entity.common;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class Artist {
    long idArtist;
    @NonNull
    String slug;
    @NonNull
    String name;
    LocalDate birthDate;
    LocalDate deathDate;
    String bio;
    String photoUrl;
    @NonNull
    Instant createdAt;
    Instant updatedAt;
    List<CastAndCrew> castAndCrew;
}
