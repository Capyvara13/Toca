package com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.gamePlatform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GamePlatformResponseDto {

    /**
     * The ID of the Game entity in the association.
     * Corresponds to 'fk_game_id' in the composite key.
     */
    private Long gameId;

    /**
     * The ID of the Platform entity in the association.
     * Corresponds to 'fk_platform_id' in the composite key.
     */
    private Long platformId;

    /**
     * The timestamp indicating when this platform was associated with the game.
     * This field is managed by the system.
     */
    private Instant addedAt;

    // Omitido: As entidades Game e Platform completas (FetchType.LAZY)
    // Omitido: O objeto GamePlatformIdEntity (chave composta)
}