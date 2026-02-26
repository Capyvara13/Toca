package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import com.atdo.toca_cms.domain.entity.Media;
import org.springframework.stereotype.Component;

@Component
public class MediaPersistenceMapper {
    public MediaEntity toEntity(Media domain) {
        if (domain == null) return null;
        MediaEntity entity = new MediaEntity();
        entity.setMediaId((domain.getMediaId()));
        entity.setMediaType(domain.getMediaType());
        entity.setCastAndCrews(domain.getCastAndCrews());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public Media toDomain(MediaEntity entity) {
        if (entity == null) return null;

        return Media.builder()
                .mediaId(entity.getMediaId())
                .mediaType(entity.getMediaType())
                .castAndCrews(entity.getCastAndCrews())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
