package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.common.CastAndCrewEntity;
import com.atdo.toca_cms.domain.entity.common.CastAndCrew;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CastAndCrewPersistenceMapper {
    private final MediaPersistenceMapper mediaMapper;
    private final ArtistPersistenceMapper artistMapper;

    public CastAndCrewEntity toEntity(CastAndCrew domain) {
        if (domain == null) return null;

        CastAndCrewEntity entity = new CastAndCrewEntity();
        entity.setIdCastAndCrew(domain.getIdCastAndCrew());
        entity.setMedia(mediaMapper.toEntity(domain.getMedia()));
        entity.setArtist(artistMapper.toEntity(domain.getArtist()));
        entity.setRoleType(domain.getRoleType());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        return entity;
    }

    public CastAndCrew toDomain(CastAndCrewEntity entity) {
        if (entity == null) return null;

        return CastAndCrew.builder()
                .idCastAndCrew(entity.getIdCastAndCrew())
                .media(mediaMapper.toDomain(entity.getMedia()))
                .artist(artistMapper.toDomain(entity.getArtist()))
                .roleType(entity.getRoleType())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public List<CastAndCrewEntity> toEntityList(List<CastAndCrew> domains) {
        if (domains == null) return Collections.emptyList();
        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<CastAndCrew> toDomainList(List<CastAndCrewEntity> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
