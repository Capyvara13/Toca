package com.atdo.toca_cms.application.dto.article;

import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDto {

    /**
     * The unique identifier of the article.
     */
    private Long idArticle;

    /**
     * The URL-friendly identifier for the article.
     */
    private String slug;

    /**
     * The ID of the User who is the author of the article.
     */
    private Long authorId;

    /**
     * The ID of the Media entity this article refers to (e.g., a Movie, Book, or Game).
     */
    private Long mediaId;

    /**
     * The title of the article.
     */
    private String title;

    /**
     * The main body content of the article.
     */
    private String content;

    /**
     * A brief summary of the article content.
     */
    private String summary;

    /**
     * The total number of views the article has received.
     */
    private Long viewCount;

    /**
     * The current status of the content (e.g., DRAFT, PUBLISHED, ARCHIVED).
     */
    private ContentStatus status;

    /**
     * If the content has or not Artificial Intelligence help.
     */
    private Boolean hasAI;

    /**
     * The instant when the article was created.
     */
    private Instant createdAt;

    /**
     * The instant when the article was last updated.
     */
    private Instant updatedAt;
}