package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.CastAndCrewPersistenceAdapter;
import com.atdo.toca_cms.application.dto.castAndCrew.CastAndCrewFilterDto;
import com.atdo.toca_cms.domain.entity.common.CastAndCrew;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CastAndCrewUsecase {
    @Autowired
    private final CastAndCrewPersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public CastAndCrew searchOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Cast and Crew not found with this id!"));
    }

    @Transactional(readOnly = true)
    public Page<CastAndCrew> findAll(CastAndCrewFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public CastAndCrew save(CastAndCrew castAndCrew) {
        return adapter.save(castAndCrew);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(castAndCrew ->  {
            adapter.deleteById(id);
        });
    }
}
