package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.movie.MovieEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaMovieRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.MoviePersistenceMapper;
import com.atdo.toca_cms.application.dto.mediaTypeDto.movieTypeDto.MovieFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.movie.Movie;
import com.atdo.toca_cms.domain.repository.MovieRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.MovieSpecification;
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
public class MoviePersistenceAdapter implements MovieRepository {
    private final MoviePersistenceMapper mapper;
    private final JpaMovieRepository jpaRepository;

    @Override
    @Transactional
    public Movie save(Movie movie) {
        MovieEntity entity = mapper.toEntity(movie);
        MovieEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Movie> findAll(MovieFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<MovieEntity> specification = MovieSpecification.withFilter(filterDto);

        return jpaRepository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
