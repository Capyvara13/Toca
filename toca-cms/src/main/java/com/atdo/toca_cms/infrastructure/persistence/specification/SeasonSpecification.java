package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.SeasonEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.season.SeasonFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SeasonSpecification {
    public static Specification<SeasonEntity> withFilter(SeasonFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getSeasonNum() >= 1) {
                predicates.add(criteriaBuilder.equal(root.get("seasonNum"), filterDto.getSeasonNum()));
            }

            if (StringUtils.hasText(filterDto.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + filterDto.getTitle() + "%"));
            }

            if (StringUtils.hasText(filterDto.getSynopsis())) {
                predicates.add(criteriaBuilder.like(root.get("synopsis"), "%" + filterDto.getSynopsis() + "%"));
            }

            if (filterDto.getSerieId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("serieId"), filterDto.getSerieId()));
            }

            if (filterDto.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filterDto.getStatus()));
            }

            if (filterDto.getCreatedAt() != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdAt"), filterDto.getCreatedAt()));
            }

            if (filterDto.getUpdatedAt() != null) {
                predicates.add(criteriaBuilder.equal(root.get("updatedAt"), filterDto.getUpdatedAt()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
