package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformIdEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.gamePlatform.GamePlatformFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Game;
import com.atdo.toca_cms.domain.entity.mediaType.game.GamePlatform;
import com.atdo.toca_cms.domain.entity.mediaType.game.GamePlatformId;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer handling the {@link GamePlatform} entity,
 * which represents the association between a specific game and a specific platform.
 * Operations utilize the composite key {@link GamePlatformId}.
 */
public interface GamePlatformRepository {

    /**
     * Saves or updates a game-platform association record in the database.
     * Since {@link GamePlatform} uses a composite key, this operation typically ensures
     * the existence and integrity of the link between a game and its supported platform.
     *
     * @param gamePlatform The composite entity object to be persisted.
     * @return The persisted {@link GamePlatform} object.
     */
    GamePlatform save(GamePlatform gamePlatform, Game game);

    /**
     * Finds a specific game-platform association by its composite unique identifier.
     *
     * @param id The composite ID (an instance of {@link GamePlatformId}) containing
     * the IDs of both the game and the platform.
     * @return An {@link Optional} containing the {@link GamePlatform} entity if the
     * association is found, or an {@link Optional#empty()} otherwise.
     */
    Optional<GamePlatform> findById(GamePlatformIdEntity id);

    /**
     * Retrieves a paginated list of game-platform associations based on specific filter criteria.
     * Filters typically allow searching for associations based on game properties (e.g., game genre,
     * release year) or platform properties.
     *
     * @param filterDto DTO object containing criteria for filtering the associations,
     * along with pagination settings (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of
     * {@link GamePlatform} records that match the filter, plus pagination metadata.
     */
    Page<GamePlatform> findAll(GamePlatformFilterDto filterDto);

    void deleteById(GamePlatformIdEntity id);
}