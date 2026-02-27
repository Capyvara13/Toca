package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.platform.PlatformFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Platform;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer handling the {@link Platform} entity.
 * This repository provides standard CRUD operations and advanced search capabilities
 * for managing gaming platforms.
 */
public interface PlatformRepository {

    /**
     * Saves or updates a platform entity in the database.
     * If the {@code platform} object has an ID, it performs an update; otherwise, it performs an insert.
     *
     * @param platform The {@link Platform} object (e.g., PlayStation 5, Xbox Series X) to be persisted.
     * @return The persisted {@link Platform} object, potentially with a newly generated ID.
     */
    Platform save(Platform platform);

    /**
     * Finds a platform entity by its primary unique identifier.
     *
     * @param idPlatform The ID (primary key) of the platform to find.
     * @return An {@link Optional} containing the {@link Platform} entity if found, or
     * an {@link Optional#empty()} otherwise.
     */
    Optional<Platform> findById(Long idPlatform);

    /**
     * Retrieves a paginated list of platform entities based on specific filter criteria.
     * This method supports querying, filtering by name or other attributes, and includes
     * pagination details for efficient data retrieval.
     *
     * @param filterDto DTO object containing criteria for filtering the platforms,
     * along with pagination settings (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of
     * {@link Platform} records that match the filter, plus pagination metadata.
     */
    Page<Platform> findAll(PlatformFilterDto filterDto);

    void deleteById(Long id);
}