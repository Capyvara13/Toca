package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.application.dto.media.MediaFilterDto;
import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import com.atdo.toca_cms.domain.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MediaUsecase {
    @Autowired
    private final MediaRepository mediaRepository;

    public Media searchOrFail(Long mediaId) {
        return mediaRepository.findById(mediaId).orElseThrow(() -> new EntityNotFoundException("Media not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Media> findAll(MediaFilterDto mediaFilterDto) {
        return mediaRepository.findAll(mediaFilterDto);
    }

    @Transactional
    public Media save(Media media) {
        return mediaRepository.save(media);
    }

    @Transactional
    public void delete(Long mediaId) {
        mediaRepository.findById(mediaId).ifPresent(media -> {
            mediaRepository.deleteById(mediaId);
        });
    }
}
