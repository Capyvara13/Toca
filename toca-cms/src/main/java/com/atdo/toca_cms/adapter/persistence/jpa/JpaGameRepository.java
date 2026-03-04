package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaGameRepository extends JpaRepository<GameEntity, Long>, JpaSpecificationExecutor<GameEntity> {
    @Override
    @NonNull
    Page<GameEntity> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<GameEntity> findBySlug(String slug);
}
