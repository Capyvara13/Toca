package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformEntity;
import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformIdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGamePlatformRepository extends JpaRepository<GamePlatformEntity, GamePlatformIdEntity>, JpaSpecificationExecutor<GamePlatformEntity> {
    @Override
    @NonNull
    Page<GamePlatformEntity> findAll(@NonNull Pageable pageable);
}
