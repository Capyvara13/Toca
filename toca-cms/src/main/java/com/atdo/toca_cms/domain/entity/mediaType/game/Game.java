package com.atdo.toca_cms.domain.entity.mediaType.game;

import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Value
@Builder(toBuilder = true)
public class Game {
    long idGame;
    @NonNull
    String slug;
    @NonNull
    String title;
    @NonNull
    String synopsis;
    @NonNull
    LocalDate releaseDate;
    String dev;
    String publisher;
    String coverUrl;
    @Builder.Default
    double rating = 0.0;
    @NonNull
    ContentStatus status;
    @NonNull
    Media media;
    @NonNull
    Instant createdAt;
    Instant updatedAt;
    @Builder.Default
    Set<GamePlatform> gamePlatforms = new HashSet<>();
}
