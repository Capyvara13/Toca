package com.atdo.toca_cms.domain.entity.mediaType.serie;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder(toBuilder = true)
public class Episode {
    long idEpisode;
    @Builder.Default
    int seasonNum = 1;
    @NonNull
    String title;
    @NonNull
    String synopsis;
    String posterUrl;
    @NonNull
    Instant createdAt;
    Instant updatedAt;
    @NonNull
    Season season;
}
