package com.atdo.toca_cms.domain.entity.mediaType.music;

import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class Music {
    long idMusic;
    @NonNull
    String slug;
    @NonNull
    String title;
    String album;
    @NonNull
    String genre;
    LocalDate releaseDate;
    int durationSecond;
    String lyrics;
    String coverUrl;
    @Builder.Default
    double rating = 0.0;
    @Builder.Default
    ContentStatus status = ContentStatus.DRAFT;
    @NonNull
    Instant createdAt;
    Instant updateAt;
    @NonNull
    Media media;
}
