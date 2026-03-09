package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.EpisodePersistenceAdapter;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.episode.EpisodeFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Episode;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EpisodeUsecase {
    @Autowired
    private final EpisodePersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public Episode searchOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Episode with this id not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Episode> findAll(EpisodeFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Episode save(Episode episode) {
        return adapter.save(episode);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(episode -> {
            adapter.deleteById(id);
        });
    }
}
