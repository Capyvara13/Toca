package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GameEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.game.GameFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GameSpecification {
    public static Specification<GameEntity> withFilter(GameFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filterDto.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), filterDto.getTitle()));
            }

            if (StringUtils.hasText(filterDto.getSynopsis())) {
                predicates.add(criteriaBuilder.like(root.get("synopsis"), filterDto.getSynopsis()));
            }

            if (StringUtils.hasText(filterDto.getDev())) {
                predicates.add(criteriaBuilder.like(root.get("dev"), filterDto.getDev()));
            }

            if (StringUtils.hasText(filterDto.getPublisher())) {
                predicates.add(criteriaBuilder.like(root.get("publisher"), filterDto.getPublisher()));
            }

            if (filterDto.getReleaseDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("releaseDate"), filterDto.getReleaseDate()));
            }

            if (filterDto.getCreatedAt() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filterDto.getCreatedAt()));
            }

            if (filterDto.getUpdatedAt() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("updatedAt"), filterDto.getUpdatedAt()));
            }

            if (filterDto.getMaxRating() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxRating"), filterDto.getMaxRating()));
            }

            if (filterDto.getMinRating() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minRating"), filterDto.getMinRating()));
            }

            if (filterDto.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filterDto.getStatus()));
            }

            if (filterDto.getGamePlatforms() != null) {
                predicates.add(criteriaBuilder.equal(root.get("gamePlatforms"), filterDto.getGamePlatforms()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
