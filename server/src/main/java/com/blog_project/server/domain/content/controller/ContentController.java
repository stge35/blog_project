package com.blog_project.server.domain.content.controller;

import com.blog_project.server.domain.content.dto.ContentDto;
import com.blog_project.server.domain.content.dto.request.ContentPatchDto;
import com.blog_project.server.domain.content.dto.request.ContentPostDto;
import com.blog_project.server.domain.content.dto.response.ContentResponseDto;
import com.blog_project.server.domain.content.service.ContentService;
import com.blog_project.server.global.response.Response;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    // 생성
    @PostMapping
    public Response<Void> create(@RequestBody ContentPostDto request,
                                 Authentication authentication) {
        contentService.create(request.getTitle(), request.getBody(), authentication.getName());

        return Response.success();
    }

    // 수정
    @PatchMapping("/{contentId}")
    public Response<ContentResponseDto> update(@PathVariable Long contentId,
                                               @RequestBody ContentPatchDto request,
                                               Authentication authentication) {
        ContentDto contentDto = contentService.update(request.getTitle(), request.getBody(), authentication.getName(), contentId);

        return Response.success(ContentResponseDto.from(contentDto));
    }

    //삭제
    @DeleteMapping("/{contentId}")
    public Response<Void> delete(@PathVariable Long contentId,
                                 Authentication authentication) {
        contentService.delete(authentication.getName(), contentId);

        return Response.success();
    }

    // 단건 조회
    @GetMapping("/{contentId}")
    public Response<ContentResponseDto> get(@PathVariable Long contentId) {
        ContentDto contentDto = contentService.findById(contentId);

        return Response.success(ContentResponseDto.from(contentDto));
    }

}
