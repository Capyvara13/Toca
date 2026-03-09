package com.atdo.toca_cms.adapter.api;

import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.episode.EpisodeFilterDto;
import com.atdo.toca_cms.application.dto.mediaTypeDto.serieTypeDto.episode.EpisodeResponseDto;
import com.atdo.toca_cms.application.usecase.EpisodeUsecase;
import com.atdo.toca_cms.domain.entity.mediaType.serie.Episode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/episodes")
@RequiredArgsConstructor
public class EpisodeController {
    private final EpisodeUsecase usecase;

    @GetMapping("/{id}")
    public ResponseEntity<Episode> findById(@PathVariable Long id) {
        Episode episodeWithId = usecase.searchOrFail(id);
        return ResponseEntity.ok(episodeWithId);
    }

    @PostMapping
    public ResponseEntity<Episode> create(@RequestBody @Valid Episode episode) {
        Episode savedEpisode = usecase.save(episode);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEpisode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Episode> update(@PathVariable Long id, @RequestBody @Valid Episode episode) {
        Episode episodeWithId = episode.toBuilder().idEpisode(id).build();
        Episode updatedEpisode = usecase.save(episodeWithId);
        return ResponseEntity.ok(updatedEpisode);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usecase.delete(id);
    }

    @GetMapping
    public ResponseEntity<Page<EpisodeResponseDto>> list(EpisodeFilterDto filterDto) {
        Page<Episode> episodePage = usecase.findAll(filterDto);

        Page<EpisodeResponseDto> responsePage = episodePage.map(episode -> new EpisodeResponseDto(
                episode.getIdEpisode(),
                episode.getTitle(),
                episode.getSynopsis(),
                episode.getSeasonNum(),
                episode.getPosterUrl(),
                episode.getSeason(),
                episode.getCreatedAt(),
                episode.getUpdatedAt()
        ));
        return ResponseEntity.ok(responsePage);
    }

}
