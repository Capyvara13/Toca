package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import com.atdo.toca_cms.application.dto.media.MediaFilterDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class MediaSpecification {
    public static Specification<MediaEntity> withFilter(MediaFilterDto filterDto) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getMediaType() != null) {
                predicates.add(cb.equal(root.get("mediaType"), filterDto.getMediaType()));
            }

            if (filterDto.getMediaIds() != null) {
                predicates.add(cb.equal(root.get("mediaIds"), filterDto.getMediaIds()));
            }

            if (filterDto.getAuthorIds() != null) {
                predicates.add(cb.equal(root.get("authorIds"), filterDto.getAuthorIds()));
            }

            if (filterDto.getCreatedAfter() != null) {
                predicates.add(cb.equal(root.get("createdAfter"), filterDto.getCreatedAfter()));
            }

            if (filterDto.getCreatedBefore() != null) {
                predicates.add(cb.equal(root.get("createdBefore"), filterDto.getCreatedBefore()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
