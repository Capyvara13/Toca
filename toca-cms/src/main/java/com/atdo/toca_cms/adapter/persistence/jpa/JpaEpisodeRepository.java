package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.EpisodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEpisodeRepository extends JpaRepository<EpisodeEntity, Long>, JpaSpecificationExecutor<EpisodeEntity> {
    @Override
    @NonNull
    Page<EpisodeEntity> findAll(@NonNull Pageable pageable);
}
