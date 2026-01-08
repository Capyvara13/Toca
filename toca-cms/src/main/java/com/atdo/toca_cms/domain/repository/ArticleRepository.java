package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.article.ArticleFilterDto;
import com.atdo.toca_cms.domain.entity.Article;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Repository interface (Domain Contract) for the Article Aggregate Root.
 * <p>
 * Defines transactional and consistency operations required by the Domain Use Cases.
 * It is agnostic to the persistence technology (JPA, SQL, NoSQL, etc.), focusing
 * only on the operations needed to manage the life cycle of an Article.
 * </p>
 */
public interface ArticleRepository {

    /**
     * Saves or updates an Article entity, managing the Aggregate Root.
     * <p>
     * This is the primary method for ensuring data consistency and transactional integrity
     * within the Article's lifecycle.
     * </p>
     * @param article The Article entity to be persisted or updated.
     * @return The persisted Article entity, possibly with generated ID/timestamps.
     */
    Article save(Article article);

    /**
     * Finds an Article by its unique identifier (ID).
     * <p>
     * Required for retrieving an Aggregate for updates or specific operations where
     * the ID is known.
     * </p>
     * @param idArticle The unique ID of the Article.
     * @return An {@link Optional} containing the Article, or empty if not found.
     */
    Optional<Article> findById(Long idArticle);

    /**
     * Finds an Article by its unique, URL-friendly slug.
     * <p>
     * Commonly used for public access or when referring to the Article via a human-readable URL.
     * </p>
     * @param slug The unique slug of the Article.
     * @return An {@link Optional} containing the Article, or empty if not found.
     */
    Optional<Article> findBySlug(String slug);

    /**0,
     * Finds all Articles based on the provided filtering criteria.
     * This method consolidates the various filtering needs into a single interface method,
     * leveraging the ArticleFilterDto from the Application layer.
     *
     * @param filterDto The DTO containing search criteria and pagination information.
     * @return A page of Articles matching the filter criteria.
     */
    Page<Article> findAll(ArticleFilterDto filterDto);


}