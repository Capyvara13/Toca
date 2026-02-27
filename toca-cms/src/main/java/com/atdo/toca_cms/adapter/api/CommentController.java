package com.atdo.toca_cms.adapter.api;

import com.atdo.toca_cms.application.dto.comment.CommentFilterDto;
import com.atdo.toca_cms.application.dto.comment.CommentResponseDto;
import com.atdo.toca_cms.application.usecase.CommentUsecase;
import com.atdo.toca_cms.domain.entity.Comment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentUsecase commentUsecase;

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        Comment comment = commentUsecase.searchOrFail(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody @Valid Comment comment) {
        Comment savedComment = commentUsecase.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Comment comment) {
        Comment commentWithId = comment.toBuilder().idComment(id).build();
        Comment updatedComment = commentUsecase.save(commentWithId);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        commentUsecase.delete(id);
    }

    @GetMapping
    public ResponseEntity<Page<CommentResponseDto>> list(CommentFilterDto filterDto) {
        Page<Comment> commentPage = commentUsecase.findAll(filterDto);

        Page<CommentResponseDto> responsePage = commentPage.map(comment -> new CommentResponseDto(
                comment.getIdComment(),
                comment.getArticle().getIdArticle(),
                comment.getAuthor().getIdUser(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdateAt(),
                comment.getParent().getIdComment(),
                comment.getStatus()
        ));
        return ResponseEntity.ok(responsePage);
    }
}
