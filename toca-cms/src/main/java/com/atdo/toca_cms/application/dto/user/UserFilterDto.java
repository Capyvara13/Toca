package com.atdo.toca_cms.application.dto.user;

import com.atdo.toca_cms.domain.util.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Data Transfer Object (DTO) to encapsulate filter and pagination criteria
 * when searching for User entities.
        */
@Getter
@Setter
public class UserFilterDto {
    // --- Filtering criteria related to username and role ---
    /**
     * Filters by usernames that are identical to or related to the text in the String.
     */
    private String username;
    /**
     * Filters by email (exact match or partial depending on your Specification).
     */
    private String email;
    /**
     * Filter by level of abstraction: ADMIN, EDITOR, or COMMON.
     */
    private UserRole role;

    // --- Filtering criteria related to creation and update dates ---
    /**
     * Filters users created **on or after** this point in time (Instant).
     */
    private Instant createdAfter;

    /**
     * Filters users updated **on or after** this point in time (Instant).
     */
    private Instant updatedAfter;
    /**
     * Filters users created **before** this point in time (Instant).
     */
    private Instant createdBefore;

    // --- Pagination criteria ---
    /**
     * The **page number** to retrieve (zero-based index). Default: 0 (first page).
     */
    private Integer page = 0;

    /**
     * The maximum number of items (users) per page (page size). Default: 10.
     */
    private Integer size = 10;
}