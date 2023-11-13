package com.blog_project.server.domain.comment.controller;

import com.blog_project.server.domain.comment.dto.request.CommentPostDto;
import com.blog_project.server.domain.comment.dto.response.CommentResponseDto;
import com.blog_project.server.domain.comment.service.CommentService;
import com.blog_project.server.global.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 생성
    @PostMapping("/{contentId}/comments")
    public Response<Void> create(@PathVariable Long contentId,
                                 @RequestBody CommentPostDto request,
                                 Authentication authentication) {
        commentService.create(contentId, authentication.getName(), request.getBody());
        return Response.success();
    }

    // 전체 조회
    @GetMapping("/{contentId}/comments")
    public Response<Page<CommentResponseDto>> get(Pageable pageable,
                                                  @PathVariable Long contentId) {
        return Response.success(commentService.list(contentId, pageable).map(CommentResponseDto::from));
    }
}
