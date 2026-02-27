package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.mediaTypeDto.movieTypeDto.MovieFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.movie.Movie;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer handling the {@link Movie} entity.
 * This repository provides standard CRUD operations and advanced search capabilities
 * tailored for movies, utilizing filtering and pagination.
 */
public interface MovieRepository {

    /**
     * Saves or updates a movie entity in the database.
     * If the {@code movie} object has an ID, it performs an update; otherwise, it performs an insert.
     *
     * @param movie The {@link Movie} object to be persisted.
     * @return The persisted {@link Movie} object, potentially with a newly generated ID.
     */
    Movie save(Movie movie);

    /**
     * Finds a movie entity by its primary unique identifier.
     *
     * @param idMovie The ID (primary key) of the movie to find.
     * @return An {@link Optional} containing the {@link Movie} entity if found, or
     * an {@link Optional#empty()} otherwise.
     */
    Optional<Movie> findById(Long idMovie);

    /**
     * Finds a movie entity by its unique slug (URL-friendly identifier).
     * Slugs are often used for clean URLs and are expected to be unique across all movies.
     *
     * @param slug The unique slug string associated with the movie.
     * @return An {@link Optional} containing the {@link Movie} entity if found, or
     * an {@link Optional#empty()} otherwise.
     */
    Optional<Movie> findBySlug(String slug);

    /**
     * Retrieves a paginated list of movie entities based on specific filter criteria.
     * This method supports complex querying, filtering by attributes like genre, release year,
     * cast, and includes pagination details.
     *
     * @param filterDto DTO object containing criteria for filtering the movies,
     * along with pagination settings (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of
     * {@link Movie} records that match the filter, plus pagination metadata.
     */
    Page<Movie> findAll(MovieFilterDto filterDto);

    void deleteById(Long id);
}