package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.game.GameFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Game;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer dealing with the {@link Game} entity.
 * This repository is responsible for managing standard CRUD operations and custom search
 * operations for games, typically including platform-related information in the underlying system logic.
 */
public interface GameRepository {

    /**
     * Saves or updates a game record in the database.
     * If the {@link Game} object possesses an existing ID, the corresponding record will be updated.
     * If the ID is null, a new game record will be created.
     *
     * @param game The game object to be persisted.
     * @return The persisted game object, potentially with an updated state or a generated ID.
     */
    Game save(Game game);

    /**
     * Finds a game by its unique identifier (primary key).
     * Uses {@link Optional} to provide a safe, non-null return value,
     * which helps prevent {@code NullPointerException} if no game is found.
     *
     * @param idGame The unique ID of the game to be retrieved.
     * @return An {@link Optional} containing the game if found, or an
     * {@link Optional#empty()} if the game does not exist.
     */
    Optional<Game> findById(Long idGame);

    /**
     * Finds a game by its unique slug identifier.
     * The slug is typically a URL-friendly, human-readable identifier.
     * Uses {@link Optional} to safely handle the possibility that the game
     * might not be found.
     *
     * @param slug The unique slug string associated with the game.
     * @return An {@link Optional} containing the game if found, or an
     * {@link Optional#empty()} if no game matches the slug.
     */
    Optional<Game> findBySlug(String slug);

    /**
     * Retrieves a paginated list of games based on specific filter criteria.
     * This method is used for complex listing and search functionality, returning
     * a slice of results using the {@link Page} structure for efficiency.
     *
     * @param filterDto DTO object containing the filter criteria (e.g., genre, platform, release date)
     * and pagination information (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of games
     * that satisfy the filter, along with pagination metadata.
     */
    Page<Game> findAll(GameFilterDto filterDto);

    void deleteById(Long id);
}