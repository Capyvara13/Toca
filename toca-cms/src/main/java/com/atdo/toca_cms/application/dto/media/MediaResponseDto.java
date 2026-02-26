package com.atdo.toca_cms.application.dto.media;

import com.atdo.toca_cms.adapter.persistence.entity.common.CastAndCrewEntity;
import com.atdo.toca_cms.domain.util.enums.MediaType;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponseDto {

    /**
     * The unique identifier of the media entry.
     */
    private Long mediaId;

    /**
     * The specific type of media (e.g., MOVIE, SERIE, BOOK).
     */
    private MediaType mediaType;

    /**
     * The instant when the media record was created.
     */
    private Instant createdAt;

    /**
     * The instant when the media record was last updated.
     */
    private Instant updatedAt;


    public MediaResponseDto(long mediaId, @NonNull MediaType mediaType, @NonNull Instant createdAt, Instant updatedAt, List<CastAndCrewEntity> castAndCrews) {
    }
}