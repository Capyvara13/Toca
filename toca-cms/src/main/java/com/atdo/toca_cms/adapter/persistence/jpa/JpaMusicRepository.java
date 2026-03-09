package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.music.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaMusicRepository extends JpaRepository<MusicEntity, Long>, JpaSpecificationExecutor<MusicEntity> {
    @Override
    @NonNull
    Page<MusicEntity> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<MusicEntity> findBySlug(String slug);
}
