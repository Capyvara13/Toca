package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.ArticleEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaArticleRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.ArticlePersistenceMapper;
import com.atdo.toca_cms.application.dto.article.ArticleFilterDto;
import com.atdo.toca_cms.domain.entity.Article;
import com.atdo.toca_cms.domain.repository.ArticleRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.ArticleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArticlePersistenceAdapter implements ArticleRepository {
    private final JpaArticleRepository jpaRepository;
    private final ArticlePersistenceMapper mapper;

    @Override
    @Transactional
    public Article save(Article article) {
        ArticleEntity entity = mapper.toEntity(article);
        ArticleEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Article> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Article> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Article> findAll(ArticleFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<ArticleEntity> specification = ArticleSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
