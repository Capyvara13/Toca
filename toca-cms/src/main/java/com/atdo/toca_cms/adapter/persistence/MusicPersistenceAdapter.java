package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.music.MusicEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaMusicRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.MusicPersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.musicTypeDto.MusicFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.music.Music;
import com.atdo.toca_cms.domain.repository.MusicRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.MusicSpecification;
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
public class MusicPersistenceAdapter implements MusicRepository {
    private final MusicPersistenceMapper mapper;
    private final JpaMusicRepository jpaRepository;

    @Override
    @Transactional
    public Music save(Music music){
        MusicEntity entity = mapper.toEntity(music);
        MusicEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Music> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Music> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Music> findAll(MusicFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<MusicEntity> specification = MusicSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
