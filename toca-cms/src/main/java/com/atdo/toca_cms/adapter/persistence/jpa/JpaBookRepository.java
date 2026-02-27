package com.atdo.toca_cms.adapter.persistence.jpa;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
    @Override
    @NonNull
    Page<BookEntity> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<BookEntity> findBySlug(String slug);
}
