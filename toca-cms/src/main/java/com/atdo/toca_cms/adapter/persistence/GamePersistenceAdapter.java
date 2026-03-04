package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GameEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaGameRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.GamePersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.game.GameFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Game;
import com.atdo.toca_cms.domain.repository.GameRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.GameSpecification;
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
public class GamePersistenceAdapter implements GameRepository {
    private final GamePersistenceMapper mapper;
    private final JpaGameRepository jpaRepository;

    @Override
    @Transactional
    public Game save(Game game) {
        GameEntity entity = mapper.toEntity(game);
        GameEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Game> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Game> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Game> findAll(GameFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() :10
        );

        Specification<GameEntity> specification = GameSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
