package com.atdo.toca_cms.domain.entity.mediaType.movie;

import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class Movie {
    long idMovie;
    @NonNull
    String slug;
    @NonNull
    String title;
    @NonNull
    LocalDate releaseDate;
    int durationMinute;
    @NonNull
    String synopsis;
    String posterUrl;
    @Builder.Default
    double rating = 0.0;
    @Builder.Default
    ContentStatus status = ContentStatus.DRAFT;
    @NonNull
    Instant createdAt;
    Instant updatedAt;
    @NonNull
    Media media;
}
