package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.SerieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaSerieRepository extends JpaRepository<SerieEntity, Long>, JpaSpecificationExecutor<SerieEntity> {
    @Override
    @NonNull
    Page<SerieEntity> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<SerieEntity> findBySlug(String slug);
}
