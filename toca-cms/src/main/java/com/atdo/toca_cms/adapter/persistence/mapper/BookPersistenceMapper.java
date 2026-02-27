package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.book.BookEntity;
import com.atdo.toca_cms.domain.entity.mediaType.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookPersistenceMapper {
    private final MediaPersistenceMapper mediaPersistenceMapper;

    public BookEntity toEntity(Book domain) {
        if (domain == null) return null;

        BookEntity entity = new BookEntity();
        entity.setIdBook(domain.getIdBook());
        entity.setSlug(domain.getSlug());
        entity.setTitle(domain.getTitle());
        entity.setSubtitle(domain.getSubtitle());
        entity.setIsbn(domain.getIsbn());
        entity.setPublicationYear(domain.getPublicationYear());
        entity.setEdition(domain.getEdition());
        entity.setPublisher(domain.getPublisher());
        entity.setNumPages(domain.getNumPages());
        entity.setSinopsis(domain.getSinopsis());
        entity.setCoverUrl(domain.getCoverUrl());
        entity.setRating(domain.getRating());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setMediaEntity(mediaPersistenceMapper.toEntity(domain.getMedia()));

        return entity;
    }

    public Book toDomain(BookEntity entity) {
        if (entity == null) return null;

        return Book.builder()
                .idBook(entity.getIdBook())
                .slug(entity.getSlug())
                .title(entity.getTitle())
                .subtitle(entity.getSubtitle())
                .isbn(entity.getIsbn())
                .publicationYear(entity.getPublicationYear())
                .edition(entity.getEdition())
                .publisher(entity.getPublisher())
                .numPages(entity.getNumPages())
                .sinopsis(entity.getSinopsis())
                .coverUrl(entity.getCoverUrl())
                .rating(entity.getRating())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .media(mediaPersistenceMapper.toDomain(entity.getMediaEntity()))
                .build();
    }
}