package com.blog_project.server.domain.content.repository;

import com.blog_project.server.domain.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContentRepository extends JpaRepository<Content, Long> {
    Content findByTitle(String title);
    List<Content> findByContentId(Long contentId);
}

