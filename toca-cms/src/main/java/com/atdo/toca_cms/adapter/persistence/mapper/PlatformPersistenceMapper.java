package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.PlatformEntity;
import com.atdo.toca_cms.domain.entity.mediaType.game.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlatformPersistenceMapper {
    private GamePlatformPersistenceMapper gamePlatformMapper;

    public PlatformEntity toEntity(Platform domain) {
        if (domain == null) return null;

        PlatformEntity entity = new PlatformEntity();
        entity.setIdPlatform(domain.getIdPlatform());
        entity.setName(domain.getName());
        entity.setManufacturer(domain.getManufacturer());
        entity.setLogoUrl(domain.getLogoUrl());
        entity.setReleaseDate(domain.getReleaseDate());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public Platform toDomain(PlatformEntity entity) {
        if (entity == null) return null;

        return Platform.builder()
                .idPlatform(entity.getIdPlatform())
                .name(entity.getName())
                .logoUrl(entity.getLogoUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .releaseDate(entity.getReleaseDate())
                .manufacturer(entity.getManufacturer())
                .build();
    }
}
