package com.atdo.toca_cms.application.dto.mediaTypeDto.bookTypeDto;

import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {

    /**
     * The unique identifier of the book record.
     */
    private Long idBook;

    /**
     * The URL-friendly identifier for the book.
     */
    private String slug;

    /**
     * The official title of the book.
     */
    private String title;

    /**
     * The optional subtitle of the book.
     */
    private String subtitle;

    /**
     * The unique ISBN (13-digit format).
     */
    private String isbn;

    /**
     * The year the book was published.
     */
    private Integer publicationYear;

    /**
     * The edition number.
     */
    private Integer edition;

    /**
     * The name of the publisher.
     */
    private String publisher;

    /**
     * The total number of pages in the book.
     */
    private Integer numPages;

    /**
     * A detailed synopsis or plot summary.
     */
    private String sinopsis;

    /**
     * URL for the book's cover image.
     */
    private String coverUrl;

    /**
     * The calculated rating of the book (e.g., from 0.0 to 10.0).
     */
    private BigDecimal rating;

    /**
     * The content status (e.g., DRAFT, PUBLISHED).
     */
    private ContentStatus status;

    /**
     * The instant when the book record was created.
     */
    private Instant createdAt;

    /**
     * The instant when the book record was last updated.
     */
    private Instant updatedAt;

    /**
     * The ID of the parent Media entity.
     */
    private Long mediaId;

    public BookResponseDto(long idBook, @NonNull String slug, @NonNull String title, String subtitle, @NonNull String isbn, int publicationYear, int edition, @NonNull String publisher, int numPages, @NonNull String sinopsis, String coverUrl, BigDecimal rating, @NonNull ContentStatus status, @NonNull Instant createdAt, Instant updatedAt) {
    }
}