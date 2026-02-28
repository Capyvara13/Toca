package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.common.CastAndCrewEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaCastAndCrewRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.CastAndCrewPersistenceMapper;
import com.atdo.toca_cms.application.dto.castAndCrew.CastAndCrewFilterDto;
import com.atdo.toca_cms.domain.entity.common.CastAndCrew;
import com.atdo.toca_cms.domain.repository.CastAndCrewRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.CastAndCrewSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CastAndCrewPersistenceAdapter implements CastAndCrewRepository {
    private final JpaCastAndCrewRepository jpaRepository;
    private final CastAndCrewPersistenceMapper mapper;

    @Override
    @Transactional
    public CastAndCrew save(CastAndCrew castAndCrew) {
        CastAndCrewEntity entity = mapper.toEntity(castAndCrew);
        CastAndCrewEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CastAndCrew> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CastAndCrew> findAll(CastAndCrewFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<CastAndCrewEntity> specification = CastAndCrewSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}