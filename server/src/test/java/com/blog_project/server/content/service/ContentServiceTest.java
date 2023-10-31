package com.blog_project.server.content.service;

import com.blog_project.server.domain.content.dto.ContentResponseDto;
import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.content.repository.ContentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@DataJpaTest
@Transactional
public class ContentServiceTest {

    @InjectMocks
    private ContentService target = new ContentService();

    @Mock
    private ContentRepository contentRepository;

    @DisplayName("컨텐츠 추가 성공")
    @Test
    public void 컨텐츠_추가_성공() {

        // given

        doReturn(content()).when(contentRepository).save(any(Content.class));

        // when

        final ContentResponseDto result = target.addContent(content().getTitle(), content().getBody());

        // then

        assertThat(result.getTitle()).isNotNull();
        assertThat(result.getTitle()).isEqualTo("content test title");
    }

    private Content content() {
        return Content.builder()
                .title("content test title")
                .body("content test body")
                .createdAt(LocalDateTime.of(2023, 10, 31, 16, 42))
                .modifiedAt(LocalDateTime.of(2023, 10, 31, 17, 52))
                .build();
    }

    @Transactional
    private class ContentService {
        public ContentResponseDto addContent(final String title, final String body) {

            final Content content = Content.builder()
                    .title(title)
                    .body(body)
                    .build();

            final Content saveContent = contentRepository.save(content);

            return ContentResponseDto.builder()
                    .title(saveContent.getTitle())
                    .body(saveContent.getBody())
                    .build();
        }
    }



}
