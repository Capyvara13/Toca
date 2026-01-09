package com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(name = "season")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SeasonEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_season")
    private long idSeason;

    @Column(name = "season_num", nullable = false, columnDefinition = "INT DEFAULT 1")
    private int seasonNum = 1;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "synopsis", nullable = false)
    @Lob
    private String synopsis;

    @Column(name = "poster_url")
    private String posterUrl;

    @JoinColumn(name = "fk_serie_season", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private SerieEntity serieEntity;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EpisodeEntity> episodes;
}
