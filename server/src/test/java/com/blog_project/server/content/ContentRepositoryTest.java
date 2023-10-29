package com.blog_project.server.content;

import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.content.repository.ContentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    @Test
    public void 컨텐츠저장테스트() {

        // given
        final Content content = Content.builder()
                .title("content title")
                .body("content body")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

        // when

        Content contentEntity = contentRepository.save(content);


        // then

        assertThat(contentEntity.getTitle()).isEqualTo("content title");
        assertThat(contentEntity.getBody()).isEqualTo("content body");
    }

    @Test
    public void 컨텐츠검색테스트() {

        final Content content = Content.builder()
                .title("content title")
                .body("content body")
                .build();

        Content contentEntity = contentRepository.save(content);
        Optional<Content> findContent = contentRepository.findById(content.getId());

        assertThat(findContent.isPresent());
        assertThat(findContent.get().getId().equals(content.getId()));
    }
}
