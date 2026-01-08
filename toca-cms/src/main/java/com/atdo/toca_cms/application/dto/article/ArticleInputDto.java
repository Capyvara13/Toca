package com.atdo.toca_cms.application.dto.article;

import com.atdo.toca_cms.domain.util.enums.ContentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInputDto {

    /**
     * The ID of the User who is the author of the article.
     * Mandatory field. Replaces the User entity.
     */
    @NotNull(message = "The author ID is mandatory.")
    private Long authorId;

    /**
     * The ID of the Media entity this article refers to (e.g., a Movie, Book, or Game).
     * Mandatory field. Replaces the Media entity.
     */
    @NotNull(message = "The media ID is mandatory.")
    private Long mediaId;

    /**
     * The title of the article.
     * Mandatory and must have a maximum of 255 characters.
     */
    @NotBlank(message = "The article must have a title.")
    @Size(max = 255, message = "The title can only have 255 characters.")
    private String title;

    /**
     * The main body content of the article.
     * Mandatory field.
     */
    @NotBlank(message = "The article needs content in its body.")
    private String content;

    /**
     * A brief summary of the article content.
     * Mandatory and must have a maximum of 500 characters.
     */
    @NotBlank(message = "The article needs a summary.")
    @Size(max = 500, message = "The summary can only have 500 characters.")
    private String summary;

    /**
     * The status of the content (e.g., DRAFT, PUBLISHED, ARCHIVED).
     * Optional field. If null, the entity default (DRAFT) will be used.
     */
    private ContentStatus status;

    /**
     * If the content has or not Artificial Intelligence help.
     */
    private Boolean hasAI;
}