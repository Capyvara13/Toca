package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.CommentEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaCommentRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.CommentPersistenceMapper;
import com.atdo.toca_cms.application.dto.comment.CommentFilterDto;
import com.atdo.toca_cms.domain.entity.Comment;
import com.atdo.toca_cms.domain.repository.CommentRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.CommentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;
    private final CommentPersistenceMapper commentPersistenceMapper;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        CommentEntity entity = commentPersistenceMapper.toEntity(comment);
        CommentEntity savedEntity = jpaCommentRepository.save(entity);
        return commentPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long id) {
        return jpaCommentRepository.findById(id).map(commentPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Comment> findAll(CommentFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
               filterDto.getPage() != null ? filterDto.getPage() : 0,
               filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<CommentEntity> specification = CommentSpecification.withFilter(filterDto);

        return jpaCommentRepository.findAll(specification, pageable).map(commentPersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(Long commentId) {
        jpaCommentRepository.deleteById(commentId);
    }
}
