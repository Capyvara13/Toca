package com.atdo.toca_cms.domain.entity.mediaType.serie;

import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class Serie {
    long idSerie;
    @NonNull
    String slug;
    @NonNull
    String title;
    @NonNull
    String originalTitle;
    @NonNull
    LocalDate startDate;
    LocalDate endDate;
    @Builder.Default
    int numSeasons = 1;
    @Builder.Default
    int numEpisodes = 1;
    @NonNull
    String synopsis;
    String posterUrl;
    @Builder.Default
    double rating = 0.0;
    @Builder.Default
    ContentStatus status = ContentStatus.DRAFT;
    @NonNull
    Instant createdAt;
    Instant updateAt;
    @NonNull
    Media media;
    List<Season> seasons;
}
