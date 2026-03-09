package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.MediaPersistenceAdapter;
import com.atdo.toca_cms.application.dto.media.MediaFilterDto;
import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MediaUsecase {
    @Autowired
    private final MediaPersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public Media searchOrFail(Long mediaId) {
        return adapter.findById(mediaId).orElseThrow(() -> new EntityNotFoundException("Media not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Media> findAll(MediaFilterDto mediaFilterDto) {
        return adapter.findAll(mediaFilterDto);
    }

    @Transactional
    public Media save(Media media) {
        return adapter.save(media);
    }

    @Transactional
    public void delete(Long mediaId) {
        adapter.findById(mediaId).ifPresent(media -> {
            adapter.deleteById(mediaId);
        });
    }
}
