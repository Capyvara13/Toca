package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.common.ArtistEntity;
import com.atdo.toca_cms.domain.entity.common.Artist;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ArtistPersistenceMapper {

    private final CastAndCrewPersistenceMapper castAndCrewMapper;

    public ArtistPersistenceMapper (@Lazy CastAndCrewPersistenceMapper castAndCrewMapper) {
        this.castAndCrewMapper = castAndCrewMapper;
    }

    public ArtistEntity toEntity(Artist domain) {
        if (domain == null) return null;

        ArtistEntity entity = new ArtistEntity();
        entity.setIdArtist(domain.getIdArtist());
        entity.setSlug(domain.getSlug());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setName(domain.getName());
        entity.setBio(domain.getBio());
        entity.setBirthDate(domain.getBirthDate());
        entity.setDeathDate(domain.getDeathDate());
        entity.setPhotoUrl(domain.getPhotoUrl());
        entity.setCastAndCrew(castAndCrewMapper.toEntityList(domain.getCastAndCrew()));

        return entity;
    }

    public Artist toDomain(ArtistEntity entity) {
        if (entity == null) return null;

        return Artist.builder()
                .idArtist(entity.getIdArtist())
                .slug(entity.getSlug())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .name(entity.getName())
                .bio(entity.getBio())
                .birthDate(entity.getBirthDate())
                .deathDate(entity.getDeathDate())
                .photoUrl(entity.getPhotoUrl())
                .castAndCrew(castAndCrewMapper.toDomainList(entity.getCastAndCrew()))
                .build();
    }
}
