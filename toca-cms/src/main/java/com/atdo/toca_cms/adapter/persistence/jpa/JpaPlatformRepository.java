package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.PlatformEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPlatformRepository extends JpaRepository<PlatformEntity, Long>, JpaSpecificationExecutor<PlatformEntity> {
    @Override
    @NonNull
    Page<PlatformEntity> findAll(@NonNull Pageable pageable);
}
