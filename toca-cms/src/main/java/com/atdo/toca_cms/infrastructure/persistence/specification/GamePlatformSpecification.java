package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.gamePlatform.GamePlatformFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GamePlatformSpecification {
    public static Specification<GamePlatformEntity> withFilter(GamePlatformFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getGame() != null) {
                predicates.add(criteriaBuilder.equal(root.get("game"), filterDto.getGame()));
            }

            if (filterDto.getPlatform() != null) {
                predicates.add(criteriaBuilder.equal(root.get("platform"), filterDto.getPlatform()));
            }

            if (filterDto.getCreatedAfter() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAfter"), filterDto.getCreatedAfter()));
            }

            if (filterDto.getCreatedBefore() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdBefore"), filterDto.getCreatedBefore()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}