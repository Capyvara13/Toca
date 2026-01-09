package com.atdo.toca_cms.domain.entity.common;

import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.util.enums.RoleType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder(toBuilder = true)
public class CastAndCrew {
    long idCastAndCrew;
    @NonNull
    Media media;
    @NonNull
    Artist artist;
    @NonNull
    RoleType roleType;
    @NonNull
    Instant createdAt;
    Instant updatedAt;
}
