package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.common.ArtistEntity;
import com.atdo.toca_cms.application.dto.artist.ArtistFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArtistSpecification {
    public static Specification<ArtistEntity> withFilter(ArtistFilterDto filterDto) {
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filterDto.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filterDto.getName().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(filterDto.getBio())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("bio")), "%" + filterDto.getBio() + "%"));
            }

            if (filterDto.getMinBirthDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("minBirthDate"), filterDto.getMinBirthDate()));
            }

            if (filterDto.getMaxBirthDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("maxBirthDate"), filterDto.getMaxBirthDate()));
            }

            if (filterDto.getMinDeathDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("minDeathDate"), filterDto.getMinDeathDate()));
            }

            if (filterDto.getMaxDeathDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("maxDeathDate"), filterDto.getMaxDeathDate()));
            }

            if (filterDto.getCreatedAt() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filterDto.getCreatedAt()));
            }

            if (filterDto.getUpdatedAt() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("updatedAt"), filterDto.getUpdatedAt()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
