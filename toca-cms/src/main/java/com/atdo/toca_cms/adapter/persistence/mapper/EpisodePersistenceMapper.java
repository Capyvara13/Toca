package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.EpisodeEntity;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Episode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EpisodePersistenceMapper {
    private final SeasonPersistenceMapper seasonMapper;

    public EpisodeEntity toEntity(Episode domain) {
        if (domain == null) return null;

        EpisodeEntity entity = new EpisodeEntity();
        entity.setIdEpisode(domain.getIdEpisode());
        entity.setSeason(seasonMapper.toEntity(domain.getSeason()));
        entity.setTitle(domain.getTitle());
        entity.setSynopsis(domain.getSynopsis());
        entity.setPosterUrl(domain.getPosterUrl());
        entity.setSeasonNum(domain.getSeasonNum());

        return entity;
    }

    public Episode toDomain(EpisodeEntity  entity) {
        if (entity == null) return null;

        return Episode.builder()
                .idEpisode(entity.getIdEpisode())
                .season(seasonMapper.toDomain(entity.getSeason()))
                .title(entity.getTitle())
                .synopsis(entity.getSynopsis())
                .posterUrl(entity.getPosterUrl())
                .seasonNum(entity.getSeasonNum())
                .build();
    }

    public List<EpisodeEntity> toEntityList(List<Episode> domains) {
        if (domains == null) return Collections.emptyList();
        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<Episode> toDomainList(List<EpisodeEntity> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
