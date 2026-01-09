package com.atdo.toca_cms.domain.entity.mediaType.serie;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class Season {
    long idSeason;
    @Builder.Default
    int seasonNum = 1;
    @NonNull
    String title;
    @NonNull
    String synopsis;
    String posterUrl;
    @NonNull
    Serie serie;
    @NonNull
    Instant createdAt;
    Instant updatedAt;
    List<Episode> episodes;
}
