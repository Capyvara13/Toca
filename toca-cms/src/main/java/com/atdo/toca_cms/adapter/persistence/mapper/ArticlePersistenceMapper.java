package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.ArticleEntity;
import com.atdo.toca_cms.domain.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticlePersistenceMapper {

    private final UserPersistenceMapper userMapper;
    private final MediaPersistenceMapper mediaMapper;

    public ArticleEntity toEntity(Article domain) {
        if (domain == null) return null;

        ArticleEntity entity = new ArticleEntity();
        entity.setIdArticle(domain.getIdArticle());
        entity.setSlug(domain.getSlug());
        entity.setTitle(domain.getTitle());
        entity.setContent(domain.getContent());
        entity.setSummary(domain.getSummary());
        entity.setHasAI(domain.isHasAI());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        entity.setAuthor(userMapper.toEntity(domain.getAuthor()));
        entity.setMedia(mediaMapper.toEntity(domain.getMedia()));

        return entity;
    }
    public Article toDomain(ArticleEntity entity) {
        if (entity == null) return null;

        return Article.builder()
                .idArticle(entity.getIdArticle())
                .slug(entity.getSlug())
                .title(entity.getTitle())
                .content(entity.getContent())
                .summary(entity.getSummary())
                .hasAI(entity.isHasAI())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .author(userMapper.toDomain(entity.getAuthor()))
                .media(mediaMapper.toDomain(entity.getMedia()))
                .build();
    }
}