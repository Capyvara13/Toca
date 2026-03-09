package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.SeasonPersistenceAdapter;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.season.SeasonFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Season;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeasonUsecase {
    @Autowired
    private final SeasonPersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public Season searchOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Season not found with this id!"));
    }

    @Transactional(readOnly = true)
    public Page<Season> findAll(SeasonFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Season save(Season season) {
        return adapter.save(season);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(season -> {
            adapter.deleteById(id);
        });
    }
}
