package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.PlatformEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.platform.PlatformFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PlatformSpecification {
    public static Specification<PlatformEntity> withFilter(PlatformFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filterDto.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filterDto.getName() + "%"));
            }

            if (StringUtils.hasText(filterDto.getManufacturer())) {
                predicates.add(criteriaBuilder.like(root.get("manufacturer"), "%" + filterDto.getManufacturer() + "%"));
            }

            if (filterDto.getReleaseAfterDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("releaseAfterDate"), filterDto.getReleaseAfterDate()));
            }

            if (filterDto.getReleaseBeforeDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("releaseBeforeDate"), filterDto.getReleaseBeforeDate()));
            }

            if (filterDto.getCreatedAfter() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAfter"), filterDto.getCreatedAfter()));
            }

            if (filterDto.getCreatedBefore() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdBefore"), filterDto.getCreatedBefore()));
            }

            if (filterDto.getUpdatedAfter() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("updatedAfter"), filterDto.getUpdatedAfter()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
