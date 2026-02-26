package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaMediaRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.MediaPersistenceMapper;
import com.atdo.toca_cms.application.dto.media.MediaFilterDto;
import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.repository.MediaRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.MediaSpecification;
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
public class MediaPersistenceAdapter implements MediaRepository {
    private final JpaMediaRepository jpaMediaRepository;
    private final MediaPersistenceMapper mapper;

    @Override
    @Transactional
    public Media save(Media media) {
        MediaEntity entity = mapper.toEntity(media);
        MediaEntity savedEntity = jpaMediaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Media> findById(Long id) {return jpaMediaRepository.findById(id).map(mapper::toDomain);}

    @Override
    @Transactional(readOnly = true)
    public Page<Media> findAll(MediaFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<MediaEntity> specification = MediaSpecification.withFilter(filterDto);

        return jpaMediaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {jpaMediaRepository.deleteById(id);}
}
