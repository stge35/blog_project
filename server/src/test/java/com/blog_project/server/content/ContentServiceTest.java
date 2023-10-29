package com.blog_project.server.content;

import com.blog_project.server.domain.content.dto.ContentPostResponse;
import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.content.repository.ContentRepository;
import com.blog_project.server.domain.content.service.ContentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ContentServiceTest {

    @InjectMocks
    private ContentService target;
    @Mock
    private ContentRepository contentRepository;

    @Test
    public void 컨텐츠등록성공() {

        // given
        // doReturn(null).when(contentRepository).findById(1L); 필요 없는 stubbing
        doReturn(content()).when(contentRepository).save(any(Content.class));

        // when
        final ContentPostResponse result = target.postResponse("content title", "content body");
        // then

        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo("content title");
        assertThat(result.getBody()).isEqualTo("content body");
    }

    private Content content() {
        return Content.builder()
                .id(-1L)
                .title("content title")
                .body("content body")
                .build();
    }

}
