package com.atdo.toca_cms.domain.entity.mediaType.game;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder(toBuilder = true)
public class GamePlatform {
    @NotNull
    GamePlatformId id;
    @NonNull
    Game game;
    @NonNull
    Platform platform;
    @NonNull
    Instant addedAt;
}
