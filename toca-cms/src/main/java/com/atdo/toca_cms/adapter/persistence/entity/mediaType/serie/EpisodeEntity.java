package com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie;

import com.atdo.toca_cms.domain.entity.mediaType.serie.Season;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "episode", uniqueConstraints = {@UniqueConstraint(
        name = "uc_season_episode",
        columnNames = {"id_episode", "season_num"}
)})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EpisodeEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_episode")
    private long idEpisode;

    @Column(name = "season_num", nullable = false, columnDefinition = "INT DEFAULT 1")
    private int seasonNum = 1;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "synopsis", nullable = false)
    @Lob
    private String synopsis;

    @Column(name = "poster_url")
    private String posterUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_season_episode")
    private SeasonEntity seasonEntity;
}
