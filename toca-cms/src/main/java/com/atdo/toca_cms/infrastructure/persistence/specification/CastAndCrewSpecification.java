package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.common.CastAndCrewEntity;
import com.atdo.toca_cms.adapter.persistence.mapper.MediaPersistenceMapper;
import com.atdo.toca_cms.application.dto.castAndCrew.CastAndCrewFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CastAndCrewSpecification {
    public static Specification<CastAndCrewEntity> withFilter(CastAndCrewFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getMediaId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("mediaId"), filterDto.getMediaId()));
            }

            if (filterDto.getArtistId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("artistId"), filterDto.getArtistId()));
            }

            if (filterDto.getRoleType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("roleType"), filterDto.getRoleType()));
            }

            if (filterDto.getCreatedAt() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filterDto.getCreatedAt()));
            }

            if (filterDto.getUpdatedAt() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("updatedAt"), filterDto.getUpdatedAt()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
