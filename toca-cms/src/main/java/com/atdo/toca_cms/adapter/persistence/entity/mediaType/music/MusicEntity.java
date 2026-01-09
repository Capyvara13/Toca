package com.atdo.toca_cms.adapter.persistence.entity.mediaType.music;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.entity.common.CastAndCrew;
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
@Table(name = "music")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MusicEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_music")
    private long idMusic;

    @Column(name = "slug", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String slug;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "album", length = 200)
    private String album;

    @Column(name = "genre", nullable = false, length = 100)
    private String genre;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "duration_second", nullable = false)
    private int durationSecond;

    @Column(name = "lyrics")
    @Lob
    private String lyrics;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "rating", nullable = false, columnDefinition = "DECIMAL(3, 1) DEFAULT 0.0")
    private double rating = 0.0;

    @Column(name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'DRAFT'")
    @Enumerated(EnumType.STRING)
    private ContentStatus status = ContentStatus.DRAFT;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updateAt;

    @JoinColumn(name = "fk_media_music", nullable = false)
    @ManyToOne
    private MediaEntity mediaEntity;
}
