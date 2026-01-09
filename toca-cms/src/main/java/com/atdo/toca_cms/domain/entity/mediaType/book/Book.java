package com.atdo.toca_cms.domain.entity.mediaType.book;

import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@Builder(toBuilder = true)
public class Book {
    long idBook;
    @NonNull
    String slug;
    @NonNull
    String title;
    String subtitle;
    @NonNull
    String isbn;
    int publicationYear;
    @Builder.Default
    int edition = 1;
    @NonNull
    String publisher;
    int numPages;
    @NonNull
    String sinopsis;

    String coverUrl;

    @Builder.Default
    BigDecimal rating = BigDecimal.ZERO;
    @NonNull
    ContentStatus status;
    @NonNull
    Instant createdAt;

    Instant updatedAt;

    @NonNull
    Media media;
}
