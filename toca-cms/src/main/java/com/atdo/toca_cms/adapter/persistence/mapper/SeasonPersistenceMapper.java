package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.SeasonEntity;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Season;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeasonPersistenceMapper {
    private final SeriePersistenceMapper serieMapper;
    private final EpisodePersistenceMapper episodeMapper;

    public SeasonPersistenceMapper(SeriePersistenceMapper serieMapper, @Lazy EpisodePersistenceMapper episodeMapper) {
        this.serieMapper = serieMapper;
        this.episodeMapper = episodeMapper;
    }

    public SeasonEntity toEntity(Season domain) {
        if (domain == null) return null;

        SeasonEntity entity = new SeasonEntity();
        entity.setIdSeason(domain.getIdSeason());
        entity.setTitle(domain.getTitle());
        entity.setSynopsis(domain.getSynopsis());
        entity.setPosterUrl(domain.getPosterUrl());
        entity.setSeasonNum(domain.getSeasonNum());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setSerie(serieMapper.toEntity(domain.getSerie()));
        entity.setEpisodes(episodeMapper.toEntityList(domain.getEpisodes()));

        return entity;
    }

    public Season toDomain(SeasonEntity entity) {
        if (entity == null) return null;

        return Season.builder()
                .idSeason(entity.getIdSeason())
                .title(entity.getTitle())
                .synopsis(entity.getSynopsis())
                .posterUrl(entity.getPosterUrl())
                .seasonNum(entity.getSeasonNum())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .serie(serieMapper.toDomain(entity.getSerie()))
                .episodes(episodeMapper.toDomainList(entity.getEpisodes()))
                .build();
    }

    public List<SeasonEntity> toEntityList(List<Season> domains) {
        if (domains == null) return Collections.emptyList();

        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<Season> toDomainList(List<SeasonEntity> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
