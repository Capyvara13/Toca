package com.atdo.toca_cms.domain.entity;

import com.atdo.toca_cms.domain.entity.common.CastAndCrew;
import com.atdo.toca_cms.domain.util.enums.MediaType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class Media {
    long mediaId;

    @NonNull
    MediaType mediaType;

    @NonNull
    Instant createdAt;

    Instant updatedAt;

    List<CastAndCrew> castAndCrews;
}
