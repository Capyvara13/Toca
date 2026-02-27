package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.book.BookEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaBookRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.BookPersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.bookTypeDto.BookFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.book.Book;
import com.atdo.toca_cms.domain.repository.BookRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookPersistenceAdapter implements BookRepository {
    private final JpaBookRepository jpaRepository;
    private final BookPersistenceMapper mapper;

    @Override
    @Transactional
    public Book save(Book book) {
        BookEntity entity = mapper.toEntity(book);
        BookEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findAll(BookFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() :10
        );

        Specification<BookEntity> specification = BookSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
