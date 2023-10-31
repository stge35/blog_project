package com.blog_project.server.content.repositoryTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import lombok.*;

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

        final Content saveContent = contentRepository.save(content);

        // then

        assertThat(saveContent.get)

    }


    @Repository
    private interface ContentRepository extends JpaRepository<Content, Long> {

    }

    @Getter
    public class Content {

    }
}
