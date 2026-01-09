package com.atdo.toca_cms.adapter.persistence.entity.mediaType.movie;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "movie")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovieEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie")
    private long idMovie;

    @Column(name = "slug", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String slug;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "duration_minute", nullable = false)
    private int durationMinute;

    @Column(name = "synopsis", nullable = false)
    @Lob
    private String synopsis;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "rating", nullable = false, columnDefinition = "DECIMAL(3, 1) DEFAULT 0.0")
    private double rating = 0.0;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'DRAFT'")
    @Enumerated(EnumType.STRING)
    private ContentStatus status = ContentStatus.DRAFT;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "fk_media_movie", nullable = false)
    private MediaEntity mediaEntity;
}
