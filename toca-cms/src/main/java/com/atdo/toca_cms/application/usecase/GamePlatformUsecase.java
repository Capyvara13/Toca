package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.GamePlatformPersistenceAdapter;
import com.atdo.toca_cms.adapter.persistence.entity.mediaType.game.GamePlatformIdEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.gameTypeDto.gamePlatform.GamePlatformFilterDto;
import com.atdo.toca_cms.domain.entity.mediaType.game.Game;
import com.atdo.toca_cms.domain.entity.mediaType.game.GamePlatform;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GamePlatformUsecase {
    @Autowired
    private final GamePlatformPersistenceAdapter adapter;

    @Transactional(readOnly = true)
    private GamePlatform searchOrFail(GamePlatformIdEntity id) {
        return adapter.findById(id).orElseThrow(() -> new EntityNotFoundException("GamePlatform not found with this id"));
    }

    @Transactional(readOnly = true)
    public Page<GamePlatform> findAll(GamePlatformFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public GamePlatform save(GamePlatform gamePlatform, Game game) {
        return adapter.save(gamePlatform, game);
    }

    @Transactional
    public void delete(GamePlatformIdEntity id) {
        adapter.findById(id).ifPresent(gamePlatform -> {
            adapter.deleteById(id);
        });
    }
}
