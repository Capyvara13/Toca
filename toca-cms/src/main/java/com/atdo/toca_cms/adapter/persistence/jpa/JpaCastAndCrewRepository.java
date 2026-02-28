package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.common.CastAndCrewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCastAndCrewRepository extends JpaRepository<CastAndCrewEntity, Long>, JpaSpecificationExecutor<CastAndCrewEntity> {
    @Override
    @NonNull
    Page<CastAndCrewEntity> findAll(@NonNull Pageable pageable);
}
