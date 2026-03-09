package com.atdo.toca_cms.adapter.api;

import com.atdo.toca_cms.application.dto.mediaTypeDto.bookTypeDto.BookFilterDto;
import com.atdo.toca_cms.application.dto.mediaTypeDto.bookTypeDto.BookResponseDto;
import com.atdo.toca_cms.application.usecase.BookUsecase;
import com.atdo.toca_cms.domain.entity.mediaType.book.Book;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookUsecase usecase;

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book book = usecase.searchByIdOrFail(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Book> findBySlug(@PathVariable String slug) {
        Book book = usecase.searchBySlugOrFail(slug);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Valid Book book) {
        Book savedBook = usecase.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody @Valid Book book) {
        Book booktWithId = book.toBuilder().idBook(id).build();
        Book updatedBook = usecase.save(booktWithId);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usecase.delete(id);
    }

    @GetMapping
        public ResponseEntity<Page<BookResponseDto>> list(BookFilterDto filterDto) {
        Page<Book> bookPage = usecase.findAll(filterDto);

        Page<BookResponseDto> responsePage = bookPage.map(book -> new BookResponseDto(
                book.getIdBook(),
                book.getSlug(),
                book.getTitle(),
                book.getSubtitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getEdition(),
                book.getPublisher(),
                book.getNumPages(),
                book.getSinopsis(),
                book.getCoverUrl(),
                book.getRating(),
                book.getStatus(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        ));
        return ResponseEntity.ok(responsePage);
    }
}
