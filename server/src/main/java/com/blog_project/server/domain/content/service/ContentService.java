package com.blog_project.server.domain.content.service;


import com.blog_project.server.domain.content.dto.ContentPostResponse;
import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public ContentPostResponse postResponse(final String title, final String body) {
        final Content content = Content.builder()
                .title(title)
                .body(body)
                .build();

        final Content saveContent = contentRepository.save(content);

        return ContentPostResponse.builder()
                .id(saveContent.getId())
                .title(saveContent.getTitle())
                .body(saveContent.getBody())
                .build();
    }
}
