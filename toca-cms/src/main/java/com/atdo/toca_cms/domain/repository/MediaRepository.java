package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.media.MediaFilterDto;
import com.atdo.toca_cms.domain.entity.Media;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Domain Port (Repository Interface) for managing Media persistence operations.
 * This interface defines the contract that the persistence adapter must fulfill.
 * It is agnostic to the persistence technology (JPA, SQL, NoSQL, etc.).
 */
public interface MediaRepository {

    /**
     * Saves a new Media entity or updates an existing one.
     * @param media The Media entity to save.
     * @return The persisted Media entity.
     */
    Media save(Media media);

    /**
     * Retrieves a Media entity by its unique ID.
     * @param idMedia The ID of the media.
     * @return An Optional containing the Media if found, or empty otherwise.
     */
    Optional<Media> findById(Long idMedia);

    /**
     * Finds all Media entities based on the provided filtering and pagination criteria.
     *
     * @param filterDto The DTO containing search criteria, including media type,
     * author IDs, date range, and pagination information.
     * @return A page of Media entities matching the filter criteria.
     */
    Page<Media> findAll(MediaFilterDto filterDto);

    void deleteById(Long mediaId);
}