package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.MusicPersistenceAdapter;
import com.atdo.toca_cms.application.dto.mediaTypeDto.musicTypeDto.MusicFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.music.Music;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MusicUsecase {
    @Autowired
    private final MusicPersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public Music searchByIdOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Music not found with this id!"));
    }

    @Transactional(readOnly = true)
    public Music searchBySlugOrFail(String slug) {
        return adapter.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Music not found with this slug!"));
    }

    @Transactional(readOnly = true)
    public Page<Music> findAll(MusicFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Music save(Music music) {
        return adapter.save(music);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(music -> {
            adapter.deleteById(id);
        });
    }
}
