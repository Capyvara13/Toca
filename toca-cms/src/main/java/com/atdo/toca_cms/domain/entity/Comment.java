package com.atdo.toca_cms.domain.entity;

import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class Comment {
    long idComment;
    @NonNull
    Article article;
    @NonNull
    User author;
    @NonNull
    String content;
    @NonNull
    Instant createdAt;
    Instant updateAt;
    Comment parent;
    List<Comment> replies;
    @Builder.Default
    ContentStatus status = ContentStatus.PENDING_REVIEW;
}
