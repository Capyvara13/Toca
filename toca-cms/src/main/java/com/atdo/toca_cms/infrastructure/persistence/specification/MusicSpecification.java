package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.music.MusicEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.musicTypeDto.MusicFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.music.Music;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MusicSpecification {
    public static Specification<MusicEntity> withFilter(MusicFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filterDto.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), filterDto.getTitle()));
            }

            if (StringUtils.hasText(filterDto.getAlbum())) {
                predicates.add(criteriaBuilder.like(root.get("album"), filterDto.getAlbum()));
            }

            if (StringUtils.hasText(filterDto.getGenre())) {
                predicates.add(criteriaBuilder.like(root.get("genre"), filterDto.getGenre()));
            }

            if (StringUtils.hasText(filterDto.getLyrics())) {
                predicates.add(criteriaBuilder.like(root.get("lyrics"), filterDto.getLyrics()));
            }

            if (filterDto.getMinDurationSecond() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minDurationSecond"), filterDto.getMinDurationSecond()));
            }

            if (filterDto.getMaxDurationSecond() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("minDurationSecond"), filterDto.getMaxDurationSecond()));
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

            if (filterDto.getMediaId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("mediaId"), filterDto.getMediaId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
