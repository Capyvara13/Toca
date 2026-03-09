package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.SerieEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaSerieRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.SeriePersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.serie.SerieFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Serie;
import com.atdo.toca_cms.domain.repository.SerieRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.SerieSpecification;
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
public class SeriePersistenceAdapter implements SerieRepository {
    private final SeriePersistenceMapper mapper;
    private final JpaSerieRepository jpaRepository;

    @Override
    @Transactional
    public Serie save(Serie serie) {
        SerieEntity entity = mapper.toEntity(serie);
        SerieEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Serie> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Serie> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public Page<Serie> findAll(SerieFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<SerieEntity> specification = SerieSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
