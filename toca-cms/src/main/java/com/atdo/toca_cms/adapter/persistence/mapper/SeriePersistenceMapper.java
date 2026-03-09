package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.SerieEntity;
import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Serie;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SeriePersistenceMapper {
    private final SeasonPersistenceMapper seasonMapper;
    private final MediaPersistenceMapper mediaMapper;

    public SeriePersistenceMapper(MediaPersistenceMapper mediaMapper, @Lazy SeasonPersistenceMapper seasonMapper) {
        this.seasonMapper = seasonMapper;
        this.mediaMapper = mediaMapper;
    }

    public SerieEntity toEntity(Serie domain) {
        if (domain == null) return null;

        SerieEntity entity = new SerieEntity();
        entity.setIdSerie(domain.getIdSerie());
        entity.setSlug(domain.getSlug());
        entity.setTitle(domain.getTitle());
        entity.setOriginalTitle(domain.getOriginalTitle());
        entity.setStartDate(domain.getStartDate());
        entity.setEndDate(domain.getEndDate());
        entity.setNumSeasons(domain.getNumSeasons());
        entity.setNumEpisodes(domain.getNumEpisodes());
        entity.setSynopsis(domain.getSynopsis());
        entity.setPosterUrl(domain.getPosterUrl());
        entity.setRating(domain.getRating());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdateAt(domain.getUpdateAt());
        entity.setMediaEntity(mediaMapper.toEntity(domain.getMedia()));
        entity.setSeasons(seasonMapper.toEntityList(domain.getSeasons()));

        return entity;
    }

    public Serie toDomain(SerieEntity entity) {
        if (entity == null) return null;

        return Serie.builder()
                .idSerie(entity.getIdSerie())
                .slug(entity.getSlug())
                .title(entity.getTitle())
                .originalTitle(entity.getOriginalTitle())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .numSeasons(entity.getNumSeasons())
                .numEpisodes(entity.getNumEpisodes())
                .synopsis(entity.getSynopsis())
                .posterUrl(entity.getPosterUrl())
                .rating(entity.getRating())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updateAt(entity.getUpdateAt())
                .media(mediaMapper.toDomain(entity.getMediaEntity()))
                .seasons(seasonMapper.toDomainList(entity.getSeasons()))
                .build();
    }
}
