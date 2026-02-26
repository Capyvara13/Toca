package com.atdo.toca_cms.adapter.api;

import com.atdo.toca_cms.application.dto.media.MediaFilterDto;
import com.atdo.toca_cms.application.dto.media.MediaResponseDto;
import com.atdo.toca_cms.application.usecase.MediaUsecase;
import com.atdo.toca_cms.domain.entity.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaUsecase mediaUsecase;

    @GetMapping("/{mediaId}")
    public ResponseEntity<Media> findById(@PathVariable Long mediaId) {
        Media media = mediaUsecase.searchOrFail(mediaId);
        return ResponseEntity.ok(media);
    }

    @PostMapping
    public ResponseEntity<Media> update(@PathVariable Long mediaId, @RequestBody Media media) {
        Media mediaWithId = media.toBuilder().mediaId(mediaId).build();
        Media updatedMedia = mediaUsecase.save(mediaWithId);
        return ResponseEntity.ok(updatedMedia);
    }

    @DeleteMapping("/{mediaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long mediaId) {
        mediaUsecase.delete(mediaId);
    }

    @GetMapping
    public ResponseEntity<Page<MediaResponseDto>> list(MediaFilterDto filterDto) {
        Page<Media> mediaPage = mediaUsecase.findAll(filterDto);

        Page<MediaResponseDto> responsePage = mediaPage.map(media -> new MediaResponseDto(
                media.getMediaId(),
                media.getMediaType(),
                media.getCreatedAt(),
                media.getUpdatedAt(),
                media.getCastAndCrews()
        ));
        return ResponseEntity.ok(responsePage);
    }
}
