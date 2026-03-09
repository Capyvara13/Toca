package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.MoviePersistenceAdapter;
import com.atdo.toca_cms.application.dto.mediaTypeDto.movieTypeDto.MovieFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.movie.Movie;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieUsecase {
    @Autowired
    private final MoviePersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public Movie searchByIdOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found with this id!"));
    }

    @Transactional(readOnly = true)
    public Movie searchBySlugOrFail(String slug) {
        return adapter.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Movie not found with this slug!"));
    }

    @Transactional(readOnly = true)
    public Page<Movie> findAll(MovieFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Movie save(Movie movie) {
        return adapter.save(movie);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(movie -> {
            adapter.deleteById(id);
        });
    }
}
