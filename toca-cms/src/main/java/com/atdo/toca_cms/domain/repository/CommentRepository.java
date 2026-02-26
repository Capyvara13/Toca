package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.comment.CommentFilterDto;
import com.atdo.toca_cms.domain.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Defines the contract for the persistence layer of the {@link Comment} entity.
 * Responsible for all CRUD and search operations related to comments in the system.
 */
public interface CommentRepository {

    /**
     * Saves or updates a comment in the database.
     * If the {@link Comment} object possesses an existing ID, the record will be updated.
     * Otherwise, a new record will be created.
     *
     * @param comment The comment object to be persisted.
     * @return The persisted comment object, which may include a generated ID or
     * other changes made by the database.
     */
    Comment save(Comment comment);

    /**
     * Finds a comment by its unique identifier.
     * Uses {@link Optional} to safely handle the possibility that the comment
     * might not be found, thus preventing a {@code NullPointerException}.
     *
     * @param idComment The unique ID of the comment to be retrieved.
     * @return An {@link Optional} containing the comment if found, or an
     * {@link Optional#empty()} if it does not exist.
     */
    Optional<Comment> findById(Long idComment);

    /**
     * Retrieves a list of comments by applying filtering and pagination rules.
     * This method is used for complex searches and data listing, returning
     * a slice of results instead of loading all records.
     *
     * @param filterDto DTO object containing the filter criteria (e.g., by post, by status)
     * and pagination information (page number, size, and sorting).
     * @return A page of results ({@link Page}) containing the list of comments
     * that satisfy the filter, along with pagination metadata.
     */
    Page<Comment> findAll(CommentFilterDto filterDto);

    void deleteById(Long commentId);
}