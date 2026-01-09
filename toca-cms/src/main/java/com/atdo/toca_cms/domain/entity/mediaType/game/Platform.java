package com.atdo.toca_cms.domain.entity.mediaType.game;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Value
@Builder(toBuilder = true)
public class Platform {
    long idPlatform;
    @NonNull
    String name;
    String manufacturer;
    LocalDate releaseDate;
    String logoUrl;
    @NonNull
    Instant createdAt;
    Instant updatedAt;
    @Builder.Default
    Set<GamePlatform> gamePlatformEntire = new HashSet<>();
}
