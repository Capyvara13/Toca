package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.artist.ArtistFilterDto;
import com.atdo.toca_cms.domain.entity.common.Artist;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Repository interface defining the core data access operations for the Artist entity.
 * In a Clean Architecture setup, this interface represents the Domain's contract
 * for persistence, separating business rules from infrastructure details (like JPA).
 */
public interface ArtistRepository {

    /**
     * Saves a new Artist entity or updates an existing one.
     * @param artist The Artist entity to save.
     * @return The saved Artist entity, possibly with generated ID and timestamps.
     */
    Artist save(Artist artist);

    /**
     * Finds an Artist by its unique identifier.
     * @param idArtist The ID of the artist.
     * @return An Optional containing the Artist if found, or empty otherwise.
     */
    Optional<Artist> findById(Long idArtist);

    /**
     * Finds an Artist by its unique URL-friendly slug.
     * @param slug The URL slug of the artist.
     * @return An Optional containing the Artist if found, or empty otherwise.
     */
    Optional<Artist> findBySlug(String slug);

    /**
     * Finds all Artists based on the provided filtering criteria.
     * This method consolidates the various filtering needs into a single interface method,
     * leveraging the ArtistFilterDto from the Application layer.
     *
     * @param filterDto The DTO containing search criteria and pagination information.
     * @return A page of Artists matching the filter criteria.
     */
    Page<Artist> findAll(ArtistFilterDto filterDto);

    void deleteById(Long id);
}