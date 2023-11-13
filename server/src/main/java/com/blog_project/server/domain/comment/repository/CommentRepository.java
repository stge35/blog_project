package com.blog_project.server.domain.comment.repository;

import com.blog_project.server.domain.comment.entity.Comment;
import com.blog_project.server.domain.content.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByContent(Content content, Pageable pageable);
}
