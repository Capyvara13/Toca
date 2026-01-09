package com.atdo.toca_cms.adapter.persistence.entity;

import com.atdo.toca_cms.domain.entity.Comment;
import com.atdo.toca_cms.domain.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUser;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password_hash", length = 60, nullable = false)
    private String passwordHash;

    @Column(name = "bio")
    @Lob
    private String bio;

    @Column(name = "role", length = 30, columnDefinition = "VARCHAR(255) DEFAULT 'COMMON'")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.COMMON;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @Column(name = "actived", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean actived = true;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
