package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.mediaTypeDto.bookTypeDto.BookFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.book.Book;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Domain Port (Repository Interface) for managing Book persistence operations.
 * This interface defines the contract that the persistence adapter must fulfill
 * for the Book media type.
 */
public interface BookRepository {

    /**
     * Saves a new Book entity or updates an existing one.
     *
     * @param book The Book entity to save.
     * @return The persisted Book entity, potentially with updated ID or timestamps.
     */
    Book save(Book book);

    /**
     * Retrieves a Book entity by its unique identifier.
     *
     * @param idBook The ID of the book.
     * @return An Optional containing the Book if found, or empty otherwise.
     */
    Optional<Book> findById(Long idBook);

    /**
     * Retrieves a Book entity by its unique URL-friendly slug.
     *
     * @param slug The URL-friendly identifier (slug) of the book.
     * @return An Optional containing the Book if found, or empty otherwise.
     */
    Optional<Book> findBySlug(String slug);

    /**
     * Finds all Book entities based on the provided filtering and pagination criteria.
     *
     * @param filterDto The DTO containing search criteria specific to Books,
     * including filters and pagination information.
     * @return A page of Book entities matching the filter criteria.
     */
    Page<Book> findAll(BookFilterDto filterDto);

    void deleteById(Long id);
}