package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.SeasonEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaSeasonRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.SeasonPersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.season.SeasonFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Season;
import com.atdo.toca_cms.domain.repository.SeasonRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.SeasonSpecification;
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
public class SeasonPersistenceAdapter implements SeasonRepository {
    private final SeasonPersistenceMapper mapper;
    private final JpaSeasonRepository jpaRepository;

    @Override
    @Transactional
    public Season save(Season season) {
        SeasonEntity entity = mapper.toEntity(season);
        SeasonEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Season> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Season> findAll(SeasonFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize(): 10
        );

        Specification<SeasonEntity> specification = SeasonSpecification.withFilter(filterDto);

        return  jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
