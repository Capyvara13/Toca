package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.CommentPersistenceAdapter;
import com.atdo.toca_cms.application.dto.comment.CommentFilterDto;
import com.atdo.toca_cms.domain.entity.Comment;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentUsecase {
    @Autowired
    private final CommentPersistenceAdapter adapter;

    @Transactional(readOnly = true)
    public Comment searchOrFail(Long commentId) {
        return adapter.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment with 'id' matches the query not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Comment> findAll(CommentFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public Comment save(Comment comment) {
        return adapter.save(comment);
    }

    @Transactional
    public void delete(Long commentId) {
        adapter.findById(commentId).ifPresent(comment -> {
            adapter.deleteById(commentId);
        });
    }
}
