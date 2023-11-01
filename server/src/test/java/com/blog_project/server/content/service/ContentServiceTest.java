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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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

    @DisplayName("컨텐츠 추가 실패 - 관리자가 아님")
    @Test
    public void 컨텐츠_추가_실패_관리자가아님() {

        // given

        // when

        // then

    }


    @DisplayName("컨텐츠 검색 성공")
    @Test
    public void 컨텐츠_성공() {

        // given

        doReturn(Optional.of(content())).when(contentRepository).findById(content().getContentId());

        // when

        final ContentResponseDto result = target.findContent(content().getContentId());

        // then

        assertThat(result.getTitle()).isEqualTo("content test title");
        assertThat(result.getBody()).isEqualTo("content test body");
    }

    @DisplayName("컨텐츠 상세 검색 성공")
    @Test
    public void 컨텐츠_상세_검색_성공() {

        // given

        doReturn(Arrays.asList(
                Content.builder().build(),
                Content.builder().build(),
                Content.builder().build())).when(contentRepository).findByContentId(content().getContentId());

        // when

        final List<ContentResponseDto> result = target.findContents(content().getContentId());

        // then

        assertThat(result.size()).isEqualTo(3);
    }

    @DisplayName("컨텐츠 삭제 성공")
    @Test
    public void 컨텐츠_삭제_성공() {

        // given

        final Content content = content();

        doReturn(Optional.of(content)).when(contentRepository).findById(content.getContentId());

        // when

        target.deleteContent(content.getContentId());

        // then(확인 방법을 찾아보자)


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

        public ContentResponseDto findContent(final Long contentId) {

            final Optional<Content> optionalContent = contentRepository.findById(contentId);
            final Content content = optionalContent.orElseThrow(() -> new RuntimeException("존재하지 않습니다."));

            return ContentResponseDto.builder()
                    .contentId(content.getContentId())
                    .title(content.getTitle())
                    .body(content.getBody())
                    .createdAt(content.getCreatedAt())
                    .modifiedAt(content.getModifiedAt())
                    .build();
        }

        public List<ContentResponseDto> findContents(final Long contentId) {
            final List<Content> contentList = contentRepository.findByContentId(content().getContentId());

            return contentList.stream().map(content -> ContentResponseDto.builder()
                        .contentId(content().getContentId())
                        .title(content().getTitle())
                        .body(content().getBody())
                        .createdAt(content().getCreatedAt())
                        .modifiedAt(content().getModifiedAt())
                        .build())
                    .collect(Collectors.toList());
        }

        public void deleteContent(final Long contentId) {

            contentRepository.deleteById(contentId);
        }
    }



}
