package com.atdo.toca_cms.adapter.persistence.entity;

import com.atdo.toca_cms.domain.entity.Article;
import com.atdo.toca_cms.domain.entity.User;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "comment")
public class CommentEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private long idComment;

    @ManyToOne
    @JoinColumn(name = "fk_article_comment", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "fk_user_comment", nullable = false)
    private User author;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_comment_id")
    private com.atdo.toca_cms.domain.entity.Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.atdo.toca_cms.domain.entity.Comment> replies;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PENDING_REVIEW'")
    @Enumerated(EnumType.STRING)
    private ContentStatus status = ContentStatus.PENDING_REVIEW;
}
