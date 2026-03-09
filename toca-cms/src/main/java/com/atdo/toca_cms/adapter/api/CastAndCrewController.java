package com.atdo.toca_cms.adapter.api;

import com.atdo.toca_cms.application.dto.castAndCrew.CastAndCrewFilterDto;
import com.atdo.toca_cms.application.dto.castAndCrew.CastAndCrewResponseDto;
import com.atdo.toca_cms.application.usecase.CastAndCrewUsecase;
import com.atdo.toca_cms.domain.entity.common.CastAndCrew;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/castAndcrews")
@RequiredArgsConstructor
public class CastAndCrewController {
    private final CastAndCrewUsecase usecase;

    @GetMapping("/{id}")
    public ResponseEntity<CastAndCrew> findBySlug(@PathVariable Long id) {
        CastAndCrew castAndCrew = usecase.searchOrFail(id);
        return ResponseEntity.ok(castAndCrew);
    }

    @PostMapping
    public ResponseEntity<CastAndCrew> create(@RequestBody @Valid CastAndCrew castAndCrew) {
        CastAndCrew savedCastAndCrew = usecase.save(castAndCrew);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCastAndCrew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CastAndCrew> update(@PathVariable Long id, @RequestBody @Valid CastAndCrew castAndCrew) {
        CastAndCrew castAndCrewWithId = castAndCrew.toBuilder().idCastAndCrew(id).build();
        CastAndCrew updatedCastAndCrew = usecase.save(castAndCrewWithId);
        return ResponseEntity.ok(updatedCastAndCrew);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usecase.delete(id);
    }

    @GetMapping
    public ResponseEntity<Page<CastAndCrewResponseDto>> list(CastAndCrewFilterDto filterDto) {
        Page<CastAndCrew> castAndCrewPage = usecase.findAll(filterDto);

        Page<CastAndCrewResponseDto> responsePage = castAndCrewPage.map(castAndCrew -> new CastAndCrewResponseDto(
                castAndCrew.getIdCastAndCrew(),
                castAndCrew.getMedia().getMediaId(),
                castAndCrew.getArtist().getIdArtist(),
                castAndCrew.getRoleType(),
                castAndCrew.getCreatedAt(),
                castAndCrew.getUpdatedAt()
        ));

        return ResponseEntity.ok(responsePage);
    }
}
