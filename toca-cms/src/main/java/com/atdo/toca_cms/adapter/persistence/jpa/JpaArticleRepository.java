package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaArticleRepository extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor<ArticleEntity> {
    @Override
    @NonNull
    Page<ArticleEntity> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<ArticleEntity> findBySlug(String slug);
}
