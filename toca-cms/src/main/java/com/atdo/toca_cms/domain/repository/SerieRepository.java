package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.serie.SerieFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Serie;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer responsible for managing the {@link Serie} entity.
 * This repository provides standard CRUD operations and custom search capabilities
 * tailored for series records, including lookups by ID and unique slug.
 */
public interface SerieRepository {

    /**
     * Saves or updates a series entity in the database.
     * If the {@code serie} object has an ID, it performs an update; otherwise, it performs an insert.
     *
     * @param serie The {@link Serie} object to be persisted.
     * @return The persisted {@link Serie} object, potentially with a newly generated ID.
     */
    Serie save(Serie serie);

    /**
     * Finds a series entity by its primary unique identifier.
     *
     * @param idSerie The ID (primary key) of the series to find.
     * @return An {@link Optional} containing the {@link Serie} entity if found, or
     * an {@link Optional#empty()} otherwise.
     */
    Optional<Serie> findById(Long idSerie);

    /**
     * Finds a series entity by its unique slug identifier.
     * The slug is typically a URL-friendly, unique representation of the series title.
     *
     * @param slug The unique slug string of the series to find.
     * @return An {@link Optional} containing the {@link Serie} entity if found, or
     * an {@link Optional#empty()} otherwise.
     */
    Optional<Serie> findBySlug(String slug);

    /**
     * Retrieves a paginated list of series entities based on specific filter criteria.
     * This method supports querying, filtering (e.g., by genre, title, or release year),
     * and includes pagination details.
     *
     * @param filterDto DTO object containing criteria for filtering the series,
     * along with pagination settings (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of
     * {@link Serie} records that match the filter, plus pagination metadata.
     */
    Page<Serie> findAll(SerieFilterDto filterDto);

    void deleteById(Long id);
}