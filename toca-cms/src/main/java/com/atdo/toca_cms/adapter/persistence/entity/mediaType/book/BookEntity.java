package com.atdo.toca_cms.adapter.persistence.entity.mediaType.book;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@Table(name = "book")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private long idBook;

    @Column(name = "slug", nullable = false)
    @EqualsAndHashCode.Include
    private String slug;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "isbn", nullable = false, length = 13, unique = true)
    @EqualsAndHashCode.Include
    private String isbn;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "edition", columnDefinition = "INT DEFAULT 1")
    private int edition = 1;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "num_pages", nullable = false)
    private int numPages;

    @Column(name = "synopsis", nullable = false)
    @Lob
    private String sinopsis;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "rating", columnDefinition = "DECIMAL(3, 1) DEFAULT 0.0")
    private BigDecimal rating = BigDecimal.ZERO;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'DRAFT'")
    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "fk_media_id", nullable = false)
    private MediaEntity mediaEntity;
}
