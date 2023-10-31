package com.blog_project.server.content.repositoryTest;

import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.content.repository.ContentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Transactional
public class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    @DisplayName("save 성공")
    @Test
    public void 저장성공() {

        // given

        final Content content = Content.builder()
                .title("content test title")
                .body("content test body")
                .createdAt(LocalDateTime.of(2023, 10, 31, 16, 42))
                .modifiedAt(LocalDateTime.of(2023, 10, 31, 17, 52))
                .build();
        // when

        contentRepository.save(content);
        final Content findContent = contentRepository.findByTitle(content.getTitle());

        // then

        assertThat(findContent.getTitle()).isEqualTo("content test title");
        assertThat(findContent.getBody()).isEqualTo("content test body");
    }
}
