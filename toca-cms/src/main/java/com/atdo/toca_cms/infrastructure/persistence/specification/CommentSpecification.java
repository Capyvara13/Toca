package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.CommentEntity;
import com.atdo.toca_cms.application.dto.comment.CommentFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CommentSpecification {
    public static Specification<CommentEntity> withFilter(CommentFilterDto filter) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by Article ID
            if (filter.getArticleId() != null) {
                predicates.add(cb.equal(root.get("article").get("idArticle"), filter.getArticleId()));
            }

            // Filter by Author (User) ID
            if (filter.getAuthorId() != null) {
                predicates.add(cb.equal(root.get("author").get("idUser"), filter.getAuthorId()));
            }

            // Filter by Status
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }

            // Filter by Keyword (search in content body)
            if (StringUtils.hasText(filter.getKeyword())) {
                String likePattern = "%" + filter.getKeyword().toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("content")), likePattern));
            }

            // Filter by Creation Date (on or after)
            if (filter.getCreatedAfter() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filter.getCreatedAfter()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
