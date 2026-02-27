package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.mediaTypeDto.musicTypeDto.MusicFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.music.Music;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer handling the {@link Music} entity.
 * This repository provides standard CRUD operations and advanced search capabilities
 * tailored for music tracks, utilizing filtering and pagination.
 */
public interface MusicRepository {

    /**
     * Saves or updates a music entity in the database.
     * If the {@code music} object has an ID, it performs an update; otherwise, it performs an insert.
     *
     * @param music The {@link Music} object (representing a track or piece) to be persisted.
     * @return The persisted {@link Music} object, potentially with a newly generated ID.
     */
    Music save(Music music);

    /**
     * Finds a music entity by its primary unique identifier.
     *
     * @param idMusic The ID (primary key) of the music track to find.
     * @return An {@link Optional} containing the {@link Music} entity if found, or
     * an {@link Optional#empty()} otherwise.
     */
    Optional<Music> findById(Long idMusic);

    /**
     * Finds a music entity by its unique slug (URL-friendly identifier).
     * Slugs are often used for clean URLs and are expected to be unique across all music tracks.
     *
     * @param slug The unique slug string associated with the music track.
     * @return An {@link Optional} containing the {@link Music} entity if found, or
     * an {@link Optional#empty()} otherwise.
     */
    Optional<Music> findBySlug(String slug);

    /**
     * Retrieves a paginated list of music entities based on specific filter criteria.
     * This method supports complex querying, filtering by attributes like artist, genre,
     * release year, and includes pagination details.
     *
     * @param filterDto DTO object containing criteria for filtering the music tracks,
     * along with pagination settings (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of
     * {@link Music} records that match the filter, plus pagination metadata.
     */
    Page<Music> findAll(MusicFilterDto filterDto);

    void deleteById(Long id);
}