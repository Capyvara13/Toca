package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.movie.MovieEntity;
import com.atdo.toca_cms.domain.entity.mediaType.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoviePersistenceMapper {
    private final MediaPersistenceMapper mediaMapper;

    public MovieEntity toEntity(Movie domain) {
        if (domain == null) return null;

        MovieEntity entity = new MovieEntity();
        entity.setIdMovie(domain.getIdMovie());
        entity.setSlug(domain.getSlug());
        entity.setTitle(domain.getTitle());
        entity.setReleaseDate(domain.getReleaseDate());
        entity.setDurationMinute(domain.getDurationMinute());
        entity.setSynopsis(domain.getSynopsis());
        entity.setPosterUrl(domain.getPosterUrl());
        entity.setRating(domain.getRating());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setMediaEntity(mediaMapper.toEntity(domain.getMedia()));

        return entity;
    }

    public Movie toDomain(MovieEntity entity) {
        if (entity == null) return null;

        return Movie.builder()
                .idMovie(entity.getIdMovie())
                .slug(entity.getSlug())
                .title(entity.getTitle())
                .releaseDate(entity.getReleaseDate())
                .durationMinute(entity.getDurationMinute())
                .synopsis(entity.getSynopsis())
                .posterUrl(entity.getPosterUrl())
                .rating(entity.getRating())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .media(mediaMapper.toDomain(entity.getMediaEntity()))
                .build();
    }
}
