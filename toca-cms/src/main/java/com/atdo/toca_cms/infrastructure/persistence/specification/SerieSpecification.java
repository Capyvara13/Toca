package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.SerieEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.serie.SerieFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SerieSpecification {
    public static Specification<SerieEntity> withFilter(SerieFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filterDto.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + filterDto.getTitle() + "%"));
            }

            if (StringUtils.hasText(filterDto.getOriginalTitle())) {
                predicates.add(criteriaBuilder.like(root.get("originalTitle"), "%" + filterDto.getOriginalTitle() + "%"));
            }

            if (StringUtils.hasText(filterDto.getSynopsis())) {
                predicates.add(criteriaBuilder.like(root.get("synopsis"), "%" + filterDto.getSynopsis() + "%"));
            }

            if (filterDto.getMinStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minStartDate"), filterDto.getMinStartDate()));
            }

            if (filterDto.getMaxEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxEndDate"), filterDto.getMaxEndDate()));
            }

            if (filterDto.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filterDto.getStatus()));
            }

            if (filterDto.getCreatedAt() != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdAt"), filterDto.getCreatedAt()));
            }

            if (filterDto.getUpdatedAt() != null) {
                predicates.add(criteriaBuilder.equal(root.get("updatedAt"), filterDto.getStatus()));
            }

            if (filterDto.getMinNumSeasons() >= 1) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minNumSeasons"), filterDto.getMinNumSeasons()));
            }

            if (filterDto.getMaxNumSeasons() >= 1) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxNumSeasons"), filterDto.getMaxNumEpisodes()));
            }

            if (filterDto.getMinNumEpisodes() >= 1) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minNumEpisodes"), filterDto.getMinNumEpisodes()));
            }

            if (filterDto.getMaxNumEpisodes() >= 1) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxNumEpisodes"),filterDto.getMaxNumEpisodes()));
            }

            if (filterDto.getMaxRating() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxRating"), filterDto.getMaxRating()));
            }

            if (filterDto.getMinRating() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minRating"), filterDto.getMinRating()));
            }

            if (filterDto.getMediaId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("mediaId"), filterDto.getMediaId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
