package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.episode.EpisodeFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Episode;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer of the {@link Episode} entity.
 * This repository is responsible for managing CRUD operations and retrieving
 * episode data within the media type domain (specifically for series).
 */
public interface EpisodeRepository {

    /**
     * Saves or updates an episode in the database.
     * If the {@link Episode} object possesses an existing ID, the corresponding record will be updated.
     * Otherwise, a new episode record will be created.
     *
     * @param episode The episode object to be persisted.
     * @return The persisted episode object, which may include a generated ID or
     * other state changes applied by the persistence mechanism.
     */
    Episode save(Episode episode);

    /**
     * Finds an episode by its unique identifier.
     * Uses {@link Optional} to provide a safe, non-null return value,
     * which helps prevent {@code NullPointerException} when the entity is not found.
     *
     * @param idEpisode The unique ID of the episode to be retrieved.
     * @return An {@link Optional} containing the episode if found, or an
     * {@link Optional#empty()} if the episode does not exist.
     */
    Optional<Episode> findById(Long idEpisode);

    /**
     * Retrieves a paginated list of episodes based on specific filter criteria.
     * This is typically used for browsing and searching episodes, ensuring efficient
     * loading by returning a {@link Page} slice of results.
     *
     * @param filterDto DTO object containing criteria for filtering episodes (e.g., by series ID,
     * status, or season number) along with pagination settings (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of episodes
     * that match the filter criteria, along with relevant pagination metadata.
     */
    Page<Episode> findAll(EpisodeFilterDto filterDto);

    void deleteById(Long id);
}