package com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.episode;

import com.atdo.toca_cms.domain.entity.mediaType.serie.Season;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeResponseDto {

    /**
     * The unique identifier of the episode record.
     */
    private Long idEpisode;

    /**
     * The ID of the parent Season entity to which this episode belongs.
     */
    private Long seasonId;

    /**
     * The official title of the episode.
     */
    private String title;

    /**
     * The number of the season this episode belongs to.
     */
    private Integer seasonNum;

    /**
     * A detailed synopsis or plot summary of the episode.
     */
    private String synopsis;

    /**
     * URL for the episode's poster image.
     */
    private String posterUrl;

    public EpisodeResponseDto(long idEpisode, @NonNull String title, @NonNull String synopsis, int seasonNum, String posterUrl, @NonNull Season season, @NonNull Instant createdAt, Instant updatedAt) {
    }
}