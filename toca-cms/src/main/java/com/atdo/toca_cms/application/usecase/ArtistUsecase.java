package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.ArtistPersistenceAdapter;
import com.atdo.toca_cms.application.dto.artist.ArtistFilterDto;
import com.atdo.toca_cms.domain.entity.common.Artist;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArtistUsecase {
    @Autowired
    private final ArtistPersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public Artist searchByIdOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Artist not found with this id!"));
    }

    @Transactional(readOnly = true)
    public Artist searchBySlugOrFail(String slug) {
        return adapter.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Artist with this slug not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Artist> findAll(ArtistFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Artist save(Artist artist) {
        return adapter.save(artist);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(artist -> {
            adapter.deleteById(id);
        });
    }
}
