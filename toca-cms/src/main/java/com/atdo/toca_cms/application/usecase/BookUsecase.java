package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.BookPersistenceAdapter;
import com.atdo.toca_cms.application.dto.mediaTypeDto.bookTypeDto.BookFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.book.Book;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookUsecase {
    @Autowired
    private final BookPersistenceAdapter adapter;

    public Book searchOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found by id!"));
    }

    @Transactional(readOnly = true)
    public Page<Book> findAll(BookFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional(readOnly = true)
    public Book findBySlug(String slug) {
        return adapter.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Book not found by slug!"));
    }

    @Transactional
    public Book save(Book book) {
        return adapter.save(book);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(book -> {
            adapter.deleteById(id);
        });
    }
}
