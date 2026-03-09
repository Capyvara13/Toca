package com.atdo.toca_cms.adapter.api;

import com.atdo.toca_cms.application.dto.article.ArticleFilterDto;
import com.atdo.toca_cms.application.dto.article.ArticleResponseDto;
import com.atdo.toca_cms.application.usecase.ArticleUsecase;
import com.atdo.toca_cms.domain.entity.Article;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleUsecase usecase;

    @GetMapping("/{id}")
    public ResponseEntity<Article> findById(@PathVariable Long id) {
        Article article = usecase.searchByIdOrFail(id);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Article> findBySlug(@PathVariable String slug) {
        Article article = usecase.searchBySlugOrFail(slug);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody @Valid Article article) {
        Article savedArticle = usecase.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article article) {
        Article articleWithId = article.toBuilder().idArticle(id).build();
        Article updatedArticle = usecase.save(articleWithId);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usecase.delete(id);
    }

    @GetMapping
    public ResponseEntity<Page<ArticleResponseDto>> list(ArticleFilterDto filterDto) {
        Page<Article> articlePage = usecase.findAll(filterDto);

        Page<ArticleResponseDto> responsePage = articlePage.map(article -> new ArticleResponseDto(
                article.getIdArticle(),
                article.getSlug(),
                article.getAuthor(),
                article.getMedia(),
                article.getTitle(),
                article.getContent(),
                article.getSummary(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        ));

        return ResponseEntity.ok(responsePage);
    }
}
