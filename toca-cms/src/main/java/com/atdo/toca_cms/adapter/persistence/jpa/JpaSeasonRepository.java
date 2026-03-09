package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.serie.SeasonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSeasonRepository extends JpaRepository<SeasonEntity, Long>, JpaSpecificationExecutor<SeasonEntity> {
    @Override
    @NonNull
    Page<SeasonEntity> findAll(@NonNull Pageable pageable);
}
