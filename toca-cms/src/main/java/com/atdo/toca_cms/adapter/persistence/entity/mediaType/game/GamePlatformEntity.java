package com.atdo.toca_cms.adapter.persistence.entity.mediaType.game;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@Table(name = "game_platform")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GamePlatformEntity {
    @EmbeddedId
    private GamePlatformIdEntity id;

    @MapsId("gameId")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_game_id")
    private GameEntity game;

    @MapsId("platformId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_platform_id")
    private PlatformEntity platform;

    @Column(name = "added_at", nullable = false)
    @CreationTimestamp
    private Instant addedAt;
}
