package com.atdo.toca_cms.adapter.persistence.entity;

import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.entity.User;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@Table(name = "article")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticleEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article")
    private long idArticle;

    @Column(name = "slug", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String slug;

    @ManyToOne
    @JoinColumn(name = "fk_user_article", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "fk_media_article", nullable = false)
    private Media media;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @Column(name = "summary", nullable = false, length = 500)
    private String summary;

    @Column(name = "view_count", columnDefinition = "BIGINT DEFAULT 0", nullable = false)
    private long viewCount = 0;

    @Column(name = "has_AI", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean hasAI = false; // If the content has help by Artificial Intelligence

    @Column(name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'DRAFT'", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentStatus status = ContentStatus.DRAFT;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public ArticleEntity(User author, Media media, @NotBlank(message = "The article must have a title.") @Size(max = 255, message = "The title can only have 255 characters.") String title, @NotBlank(message = "The article needs content in its body.") String content, @NotBlank(message = "The article needs a summary.") @Size(max = 500, message = "The summary can only have 500 characters.") String summary, ContentStatus status) {
    }
}
