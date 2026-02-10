package com.atdo.toca_cms.adapter.persistence.jpa;


import com.atdo.toca_cms.adapter.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    @Override
    @NonNull
    Page<UserEntity> findAll(@NonNull Pageable pageable);
}
