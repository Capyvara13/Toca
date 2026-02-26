package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.CommentEntity;
import com.atdo.toca_cms.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentPersistenceMapper {
    private final ArticlePersistenceMapper articleMapper;
    private final UserPersistenceMapper userMapper;

    public CommentEntity toEntity(Comment domain) {
        if (domain == null) return null;

        CommentEntity entity = new CommentEntity();
        entity.setIdComment(domain.getIdComment());
        entity.setContent(domain.getContent());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdateAt(domain.getUpdateAt());

        entity.setArticle(articleMapper.toEntity(domain.getArticle()));
        entity.setAuthor(userMapper.toEntity(domain.getAuthor()));

        if (domain.getParent() != null) {
            entity.setParent(toEntity(domain.getParent()));
        }

        return entity;
    }

    public Comment toDomain(CommentEntity entity) {
        if (entity == null) return null;

        return Comment.builder()
                .idComment(entity.getIdComment())
                .content(entity.getContent())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updateAt(entity.getUpdateAt())
                .article(articleMapper.toDomain(entity.getArticle()))
                .author(userMapper.toDomain(entity.getAuthor()))
                .replies(entity.getReplies() != null ?
                        entity.getReplies().stream().map(this::toDomain).collect(Collectors.toList()) : null)
                .build();
    }
}