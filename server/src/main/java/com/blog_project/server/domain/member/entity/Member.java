package com.blog_project.server.domain.member.entity;


import com.blog_project.server.domain.category.entity.Category;
import com.blog_project.server.domain.comment.entity.Comment;
import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.global.audit.AuditingFields;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany
    private List<Category> categories = new ArrayList<>();

    @OneToMany
    private List<Content> contents = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();


    public static Member of(String email, String encodedPwd) {

        Member entity = new Member();
        entity.setEmail(email);
        entity.setPassword(encodedPwd);

        return entity;
    }

}
