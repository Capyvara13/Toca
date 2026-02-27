package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.castAndCrew.CastAndCrewFilterDto;
import com.atdo.toca_cms.domain.entity.common.CastAndCrew;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Domain Port (Repository Interface) for managing CastAndCrew persistence operations.
 * This interface defines the contract that the persistence adapter must fulfill
 * for the CastAndCrew common entity (representing people involved in media).
 */
public interface CastAndCrewRepository {

    /**
     * Saves a new CastAndCrew entity or updates an existing one.
     *
     * @param castAndCrew The CastAndCrew entity to save.
     * @return The persisted CastAndCrew entity, potentially with updated ID or timestamps.
     */
    CastAndCrew save(CastAndCrew castAndCrew);

    /**
     * Retrieves a CastAndCrew entity by its unique identifier.
     *
     * @param idCastAndCrew The ID of the cast or crew member.
     * @return An Optional containing the CastAndCrew entity if found, or empty otherwise.
     */
    Optional<CastAndCrew> findById(Long idCastAndCrew);

    /**
     * Finds all CastAndCrew entities based on the provided filtering and pagination criteria.
     *
     * @param filterDto The DTO containing search criteria specific to CastAndCrew,
     * including filters and pagination information.
     * @return A page of CastAndCrew entities matching the filter criteria.
     */
    Page<CastAndCrew> findAll(CastAndCrewFilterDto filterDto);

    void deleteById(Long id);
}