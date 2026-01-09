package com.atdo.toca_cms.adapter.persistence.entity.mediaType.game;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.entity.mediaType.game.GamePlatform;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "game")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GameEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game")
    private long idGame;

    @Column(name = "slug", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String slug;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "synopsis", nullable = false)
    @Lob
    private String synopsis;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "dev", nullable = false)
    private String dev;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "rating", columnDefinition = "DECIMAL(3, 1) DEFAULT 0.0")
    private double rating = 0.0;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    @JoinColumn(name = "fk_media_id", nullable = false)
    @ManyToOne
    private MediaEntity mediaEntity;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GamePlatformEntity> gamePlatforms = new HashSet<>();
}
