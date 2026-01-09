package com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Season;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "serie")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class SerieEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_serie")
    private long idSerie;

    @Column(name = "slug", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String slug;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "original_title", nullable = false)
    private String originalTitle;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "num_seasons", columnDefinition = "INT DEFAULT 1")
    private int numSeasons = 1;

    @Column(name = "num_episodes", columnDefinition = "INT DEFAULT 1")
    private int numEpisodes = 1;

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
    private Instant updateAt;

    @JoinColumn(name = "fk_media_serie", nullable = false)
    @ManyToOne
    private MediaEntity mediaEntity;

    //fk season e castAndCrew
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeasonEntity> seasons;
}
