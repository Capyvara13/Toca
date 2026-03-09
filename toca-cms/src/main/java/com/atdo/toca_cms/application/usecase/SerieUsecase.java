package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.SeriePersistenceAdapter;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.serie.SerieFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Serie;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SerieUsecase {
    @Autowired
    private final SeriePersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public Serie searchOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Serie not found with this id!"));
    }

    @Transactional(readOnly = true)
    public Serie searchBySlug(String slug) {
        return adapter.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Serie not found with this slug!"));
    }

    @Transactional(readOnly = true)
    public Page<Serie> findAll(SerieFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Serie save(Serie serie) {
        return adapter.save(serie);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(serie -> {
            adapter.deleteById(id);
        });
    }
}
