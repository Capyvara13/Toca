package com.atdo.toca_cms.application.dto.article;

import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Data Transfer Object (DTO) to encapsulate filter and pagination criteria
 * when searching for Article entities.
 */
@Getter
@Setter
public class ArticleFilterDto {

    // --- Filtering criteria related to content and authorship ---
    /**
     * Filters articles whose title or content body contains this specified keyword.
     */
    private String keyword;

    /**
     * Filters articles written by the specified Author ID.
     */
    private Long authorId;

    /**
     * Filters articles based on their moderation or publication status (e.g., DRAFT, PUBLISHED, ARCHIVED).
     */
    private ContentStatus status;

    /**
     * Filters articles that have a view count greater than or equal to this minimum value.
     */
    private long minViewCount;

    /**
     * Filters articles that have a view count lower than or equal to this maximum value.
     */
    private long maxViewCount;

    /**
     * Filters articles that have a content with Artificial Intelligence defined with TRUE or FALSE.
     */
    private Boolean hasAI;

    // --- Filtering criteria related to publication date ---
    /**
     * Filters **articles** published **on or after** this point in time (Instant).
     */
    private Instant createdAfter;


    // --- Pagination criteria ---
    /**
     * The **page number** to retrieve (zero-based index). Default: 0 (first page).
     */
    private Integer page = 0;

    /**
     * The maximum number of items (**articles**) per page (page size). Default: 10.
     */
    private Integer size = 10;

}