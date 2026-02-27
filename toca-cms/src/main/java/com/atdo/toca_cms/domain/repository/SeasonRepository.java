package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.season.SeasonFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Season;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer handling the {@link Season} entity,
 * which represents a season within a series.
 * This repository provides standard CRUD operations and search capabilities
 * for managing series seasons.
 */
public interface SeasonRepository {

    /**
     * Saves or updates a season entity in the database.
     * If the {@code season} object has an ID, it performs an update; otherwise, it performs an insert.
     *
     * @param season The {@link Season} object to be persisted.
     * @return The persisted {@link Season} object, potentially with a newly generated ID.
     */
    Season save(Season season);

    /**
     * Finds a season entity by its primary unique identifier.
     *
     * @param idSeason The ID (primary key) of the season to find.
     * @return An {@link Optional} containing the {@link Season} entity if found, or
     * an {@link Optional#empty()} otherwise.
     */
    Optional<Season> findById(Long idSeason);

    /**
     * Retrieves a paginated list of season entities based on specific filter criteria.
     * This method supports querying, filtering (e.g., by series ID, title, or release year),
     * and includes pagination details.
     *
     * @param filterDto DTO object containing criteria for filtering the seasons,
     * along with pagination settings (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of
     * {@link Season} records that match the filter, plus pagination metadata.
     */
    Page<Season> findAll(SeasonFilterDto filterDto);

    void deleteById(Long id);
}