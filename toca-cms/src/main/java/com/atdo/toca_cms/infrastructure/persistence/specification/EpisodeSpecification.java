package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.EpisodeEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.episode.EpisodeFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EpisodeSpecification {
    public static Specification<EpisodeEntity> withFilter(EpisodeFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filterDto.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), filterDto.getTitle()));
            }

            if (StringUtils.hasText(filterDto.getSynopsis())) {
                predicates.add(criteriaBuilder.like(root.get("synopsis"), filterDto.getSynopsis()));
            }

            if (filterDto.getEpisodeNum() >= 1) {
                predicates.add(criteriaBuilder.equal(root.get("episodeNum"), filterDto.getEpisodeNum()));
            }

            if (filterDto.getSeasonId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("seasonId"), filterDto.getSeasonId()));
            }

            if (filterDto.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filterDto.getStatus()));
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
