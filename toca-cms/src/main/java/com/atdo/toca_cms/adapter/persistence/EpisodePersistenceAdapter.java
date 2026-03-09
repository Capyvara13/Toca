package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.EpisodeEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaEpisodeRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.EpisodePersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.episode.EpisodeFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Episode;
import com.atdo.toca_cms.domain.repository.EpisodeRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.EpisodeSpecification;
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
public class EpisodePersistenceAdapter implements EpisodeRepository {
    private final EpisodePersistenceMapper mapper;
    private final JpaEpisodeRepository jpaRepository;

    @Override
    @Transactional
    public Episode save(Episode episode) {
        EpisodeEntity entity = mapper.toEntity(episode);
        EpisodeEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Episode> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Episode> findAll(EpisodeFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<EpisodeEntity> specification = EpisodeSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public  void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
