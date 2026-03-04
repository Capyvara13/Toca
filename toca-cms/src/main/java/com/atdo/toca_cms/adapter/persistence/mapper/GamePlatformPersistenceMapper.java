package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformEntity;
import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformIdEntity;
import com.atdo.toca_cms.domain.entity.mediaType.game.Game;
import com.atdo.toca_cms.domain.entity.mediaType.game.GamePlatform;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GamePlatformPersistenceMapper {
    private final PlatformPersistenceMapper platformMapper;
    private final GamePersistenceMapper gameMapper;

    // Construtor manual para garantir que o @Lazy funcione no parâmetro
    public GamePlatformPersistenceMapper(
            PlatformPersistenceMapper platformMapper,
            @Lazy GamePersistenceMapper gameMapper) {
        this.platformMapper = platformMapper;
        this.gameMapper = gameMapper;
    }

    public GamePlatformEntity toEntity(GamePlatform domain, Game gameDomain) {
        if (domain == null) return null;

        GamePlatformEntity entity = new GamePlatformEntity();

        // Initializes the composite ID.
        GamePlatformIdEntity id = new GamePlatformIdEntity();
        if (gameDomain != null) {
            id.setGameId(gameDomain.getIdGame());
            entity.setGame(gameMapper.toEntity(gameDomain));
        }

        id.setPlatformId(domain.getPlatform().getIdPlatform());
        entity.setPlatform(platformMapper.toEntity(domain.getPlatform()));

        entity.setId(id);
        entity.setAddedAt(domain.getAddedAt());

        return entity;
    }

    public GamePlatform toDomain(GamePlatformEntity entity) {
        if (entity == null || entity.getPlatform() == null) return null;

        return GamePlatform.builder()
                .platform(platformMapper.toDomain(entity.getPlatform()))
                .addedAt(entity.getAddedAt())
                .build();
    }

    public Set<GamePlatformEntity> toEntitySet(Set<GamePlatform> domains, Game game) {
        if (domains == null || domains.isEmpty()) return Collections.emptySet();
        return domains.stream()
                .map(domain -> toEntity(domain, game))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Set<GamePlatform> toDomainSet(Set<GamePlatformEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptySet();
        return entities.stream()
                .map(this::toDomain)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
