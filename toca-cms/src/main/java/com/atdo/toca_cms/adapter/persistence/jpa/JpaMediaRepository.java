package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.MediaEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMediaRepository extends JpaRepository<MediaEntity, Long>, JpaSpecificationExecutor<MediaEntity> {
    @Override
    @NonNull
    Page<MediaEntity> findAll(@NonNull Pageable pageable);
}
