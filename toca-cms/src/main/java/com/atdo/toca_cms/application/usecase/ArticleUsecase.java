package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.ArticlePersistenceAdapter;
import com.atdo.toca_cms.application.dto.article.ArticleFilterDto;
import com.atdo.toca_cms.domain.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleUsecase {
    @Autowired
    private final ArticlePersistenceAdapter adapter;

    public Article searchOrFail(Long id) {
        return adapter.findById(id).orElseThrow(() -> new ExpressionException("Article not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Article> findAll(ArticleFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Article save(Article article) {
        return adapter.save(article);
    }

    @Transactional
    public void delete(Long id) {
        adapter.findById(id).ifPresent(article -> {
            adapter.deleteById(id);
        });
    }
}
