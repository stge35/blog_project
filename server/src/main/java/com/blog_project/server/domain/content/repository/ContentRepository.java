package com.blog_project.server.domain.content.repository;

import com.blog_project.server.domain.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
