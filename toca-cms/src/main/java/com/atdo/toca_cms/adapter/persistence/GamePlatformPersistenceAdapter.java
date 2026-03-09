package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformEntity;
import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformIdEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaGamePlatformRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.GamePlatformPersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.gamePlatform.GamePlatformFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Game;
import com.atdo.toca_cms.domain.entity.mediaType.game.GamePlatform;
import com.atdo.toca_cms.domain.repository.GamePlatformRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.GamePlatformSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GamePlatformPersistenceAdapter implements GamePlatformRepository {
    private final GamePlatformPersistenceMapper mapper;
    private final JpaGamePlatformRepository jpaRepository;

    @Override
    @Transactional
    public GamePlatform save(GamePlatform gamePlatform, Game game) {
        if (game == null)  {
            throw new IllegalArgumentException("Game need to has a Id!");
        }
        GamePlatformEntity entity = mapper.toEntity(gamePlatform, game);
        GamePlatformEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GamePlatform> findById(GamePlatformIdEntity id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GamePlatform> findAll(GamePlatformFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<GamePlatformEntity> specification = GamePlatformSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(GamePlatformIdEntity id) {
        jpaRepository.deleteById(id);
    }
}
