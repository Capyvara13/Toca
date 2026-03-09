package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.music.MusicEntity;
import com.atdo.toca_cms.domain.entity.mediaType.music.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicPersistenceMapper {
    private final MediaPersistenceMapper mediaMapper;

    public MusicEntity toEntity(Music domain) {
        if (domain == null) return null;

        MusicEntity entity = new MusicEntity();
        entity.setIdMusic(domain.getIdMusic());
        entity.setSlug(domain.getSlug());
        entity.setTitle(domain.getTitle());
        entity.setAlbum(domain.getAlbum());
        entity.setGenre(domain.getGenre());
        entity.setReleaseDate(domain.getReleaseDate());
        entity.setDurationSecond(domain.getDurationSecond());
        entity.setLyrics(domain.getLyrics());
        entity.setCoverUrl(domain.getCoverUrl());
        entity.setRating(domain.getRating());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdateAt(domain.getUpdateAt());
        entity.setMediaEntity(mediaMapper.toEntity(domain.getMedia()));

        return entity;
    }

    public Music toDomain(MusicEntity entity) {
        if (entity == null) return  null;

        return Music.builder()
                .idMusic(entity.getIdMusic())
                .slug(entity.getSlug())
                .title(entity.getTitle())
                .album(entity.getAlbum())
                .genre(entity.getGenre())
                .releaseDate(entity.getReleaseDate())
                .durationSecond(entity.getDurationSecond())
                .lyrics(entity.getLyrics())
                .coverUrl(entity.getCoverUrl())
                .rating(entity.getRating())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updateAt(entity.getUpdateAt())
                .media(mediaMapper.toDomain(entity.getMediaEntity()))
                .build();
    }
}
