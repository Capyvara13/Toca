package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.PlatformPersistenceAdapter;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.platform.PlatformFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Platform;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlatformUsecase {
    private final PlatformPersistenceAdapter adapter;

    public Platform searchOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Platform not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Platform> findAll(PlatformFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Platform save(Platform platform) {
        return adapter.save(platform);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(platform -> {
            adapter.deleteById(id);
        });
    }
}
