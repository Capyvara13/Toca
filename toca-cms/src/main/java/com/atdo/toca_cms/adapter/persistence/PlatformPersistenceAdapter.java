package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.PlatformEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaPlatformRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.PlatformPersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.platform.PlatformFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Platform;
import com.atdo.toca_cms.domain.repository.PlatformRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.PlatformSpecification;
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
public class PlatformPersistenceAdapter implements PlatformRepository {
    private final PlatformPersistenceMapper mapper;
    private final JpaPlatformRepository jpaRepository;

    @Override
    @Transactional
    public Platform save(Platform platform) {
        PlatformEntity entity = mapper.toEntity(platform);
        PlatformEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Platform> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Platform> findAll(PlatformFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() :10
        );

        Specification<PlatformEntity> specification = PlatformSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
