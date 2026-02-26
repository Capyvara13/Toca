package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.application.dto.comment.CommentFilterDto;
import com.atdo.toca_cms.domain.entity.Comment;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import com.atdo.toca_cms.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentUsecase {
    @Autowired
    private final CommentRepository commentRepository;

    public Comment searchOrFail(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment with 'id' matches the query not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Comment> findAll(CommentFilterDto filterDto) {
        return commentRepository.findAll(filterDto);
    }

    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long commentId) {
        commentRepository.findById(commentId).ifPresent(comment -> {
            commentRepository.deleteById(commentId);
        });
    }
}
