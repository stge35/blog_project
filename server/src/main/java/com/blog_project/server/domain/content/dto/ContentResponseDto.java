package com.blog_project.server.domain.content.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@RequiredArgsConstructor
public class ContentResponseDto {

        private final Long contentId;
        private final String title;
        private final String body;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;
}
