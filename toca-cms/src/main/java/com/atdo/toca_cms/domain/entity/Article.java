package com.atdo.toca_cms.domain.entity;

import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder(toBuilder = true)
public class Article {
    long idArticle;
    @NonNull
    String slug;
    @NonNull
    User author;
    @NonNull
    Media media;
    @NonNull
    String title;
    @NonNull
    String content;
    String summary;
    @Builder.Default
    boolean hasAI = false; // If the content has help by Artificial Intelligence
    @Builder.Default
    ContentStatus status = ContentStatus.DRAFT;
    @NonNull
    Instant createdAt;
    Instant updatedAt;
}
