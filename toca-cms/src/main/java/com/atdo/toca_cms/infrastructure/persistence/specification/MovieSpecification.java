package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.movie.MovieEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.movieTypeDto.MovieFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieSpecification {
    public static Specification<MovieEntity> withFilter(MovieFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filterDto.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + filterDto.getTitle() + "%"));
            }

            if (StringUtils.hasText(filterDto.getSynopsis())) {
                predicates.add(criteriaBuilder.like(root.get("synopsis"), "%" + filterDto.getSynopsis() + "%"));
            }

            if (filterDto.getMinDurationMinute() >= 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minDurationMinute"), filterDto.getMinDurationMinute()));
            }

            if (filterDto.getMaxDurationMinute() >= 0) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxDurationMinute"), filterDto.getMaxDurationMinute()));
            }

            if (filterDto.getMaxRating() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxRating"), filterDto.getMaxRating()));
            }

            if (filterDto.getMinRating() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minRating"), filterDto.getMinRating()));
            }

            if (filterDto.getReleaseDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("releaseDate"), filterDto.getReleaseDate()));
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
