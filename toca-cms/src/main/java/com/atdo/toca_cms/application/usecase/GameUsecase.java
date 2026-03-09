package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.GamePersistenceAdapter;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.game.GameFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Game;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameUsecase {
    @Autowired
    private final GamePersistenceAdapter gameAdapter;

    @Transactional(readOnly = true)
    public Game searchByIdOfFail(Long id) {
        return gameAdapter.findById(id).orElseThrow(() -> new EntityNotFoundException("Game with this id not found!"));
    }

    @Transactional(readOnly = true)
    public Game searchBySlugOrFail(String slug) {
        return gameAdapter.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Game not found with this slug!"));
    }

    @Transactional(readOnly = true)
    public Page<Game> findAll(GameFilterDto filterDto) {
        return gameAdapter.findAll(filterDto);
    }

    @Transactional
    public Game save(Game game) {
        return gameAdapter.save(game);
    }

    @Transactional
    public void deleteById(Long id) {
        gameAdapter.findById(id).ifPresent(game -> {
            gameAdapter.deleteById(id);
        });
    }
}
