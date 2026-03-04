package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GameEntity;
import com.atdo.toca_cms.domain.entity.mediaType.game.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GamePersistenceMapper {
    private final MediaPersistenceMapper mediaMapper;
    private final GamePlatformPersistenceMapper gamePlatformMapper;

    public GameEntity toEntity(Game domain) {
        if (domain == null) return null;

        GameEntity entity = new GameEntity();
        entity.setMediaEntity(mediaMapper.toEntity(domain.getMedia()));
        entity.setIdGame(domain.getIdGame());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setDev(domain.getDev());
        entity.setCoverUrl(domain.getCoverUrl());
        entity.setPublisher(domain.getPublisher());
        entity.setRating(domain.getRating());
        entity.setReleaseDate(domain.getReleaseDate());
        entity.setSlug(domain.getSlug());
        entity.setStatus(domain.getStatus());
        entity.setSynopsis(domain.getSynopsis());
        entity.setTitle(domain.getTitle());
        entity.setGamePlatforms(gamePlatformMapper.toEntitySet(domain.getGamePlatforms(), domain));
        return entity;
    }

    public Game toDomain(GameEntity entity) {
        if (entity == null) return null;

        return Game.builder()
                .media(mediaMapper.toDomain(entity.getMediaEntity()))
                .idGame(entity.getIdGame())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .dev(entity.getDev())
                .coverUrl(entity.getCoverUrl())
                .publisher(entity.getPublisher())
                .rating(entity.getRating())
                .releaseDate(entity.getReleaseDate())
                .slug(entity.getSlug())
                .status(entity.getStatus())
                .synopsis(entity.getSynopsis())
                .title(entity.getTitle())
                .gamePlatforms(gamePlatformMapper.toDomainSet(entity.getGamePlatforms()))
                .build();
    }
}
