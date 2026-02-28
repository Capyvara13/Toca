package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.common.ArtistEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaArtistRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.ArtistPersistenceMapper;
import com.atdo.toca_cms.application.dto.artist.ArtistFilterDto;
import com.atdo.toca_cms.domain.entity.common.Artist;
import com.atdo.toca_cms.domain.repository.ArtistRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.ArtistSpecification;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArtistPersistenceAdapter implements ArtistRepository {
    private final JpaArtistRepository jpaRepository;
    private final ArtistPersistenceMapper mapper;

    @Override
    @Transactional
    public Artist save(Artist artist) {
        ArtistEntity entity = mapper.toEntity(artist);
        ArtistEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Artist> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Artist> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Artist> findAll(ArtistFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<ArtistEntity> specification = ArtistSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
