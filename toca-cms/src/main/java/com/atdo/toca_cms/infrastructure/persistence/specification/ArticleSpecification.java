package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.ArticleEntity;
import com.atdo.toca_cms.application.dto.article.ArticleFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ArticleSpecification {
    public static Specification<ArticleEntity> withFilter(ArticleFilterDto filterDto) {
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por Keyword (busca no título OU no conteúdo)
            if (StringUtils.hasText(filterDto.getKeyword())) {
                String keyword = "%" + filterDto.getKeyword().toLowerCase() + "%";
                Predicate titlePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")), keyword);
                Predicate contentPredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("content")), keyword);
                predicates.add(criteriaBuilder.or(titlePredicate, contentPredicate));
            }

            // Filtro por ID do Autor
            if (filterDto.getAuthorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("author").get("idUser"), filterDto.getAuthorId()));
            }

            // Filtro por Status (DRAFT, PUBLISHED, etc.)
            if (filterDto.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filterDto.getStatus()));
            }

            // Filtro por Inteligência Artificial
            if (filterDto.getHasAI() != null) {
                predicates.add(criteriaBuilder.equal(root.get("hasAI"), filterDto.getHasAI()));
            }

            // Filtro de Range de Visualizações (Min)
            if (filterDto.getMinViewCount() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("viewCount"), filterDto.getMinViewCount()));
            }

            // Filtro de Range de Visualizações (Max)
            if (filterDto.getMaxViewCount() > 0) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("viewCount"), filterDto.getMaxViewCount()));
            }

            // Filtro por Data de Criação (A partir de...)
            if (filterDto.getCreatedAfter() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filterDto.getCreatedAfter()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
