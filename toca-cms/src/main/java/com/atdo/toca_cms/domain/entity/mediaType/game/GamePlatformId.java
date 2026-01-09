package com.atdo.toca_cms.domain.entity.mediaType.game;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder(toBuilder = true)
public class GamePlatformId implements Serializable {
    @NonNull
    Long gameId;
    @NonNull
    Long platformId;
}

