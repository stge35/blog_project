package com.blog_project.server.content;

import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.content.repository.ContentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
public class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    @Test
    public void 컨텐츠저장테스트() {

        // given
        final Content content = Content.builder()
                .title("content title")
                .body("content body")
                .createdAt(LocalDateTime.of(2023,10,29, 19,29))
                .modifiedAt(LocalDateTime.of(2023, 10, 30, 19, 35))
                .build();

        // when

        Content contentEntity = contentRepository.save(content);

        Content result = contentRepository.findById(content.getId()).get();

        // then

        assertThat(contentEntity.getTitle()).isEqualTo("content title");
        assertThat(contentEntity).isEqualTo(result);


    }
}
