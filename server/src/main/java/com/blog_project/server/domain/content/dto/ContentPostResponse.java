package com.blog_project.server.domain.content.dto;

import com.blog_project.server.domain.content.entity.Content;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class ContentPostResponse {

    private final Long id;
    private final String title;
    private final String body;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
}
