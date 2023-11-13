package com.blog_project.server.domain.content.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContentPostDto {

    private String title;
    private String body;
}
