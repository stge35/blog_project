package com.blog_project.server.domain.category.entity;

import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.global.audit.AuditingFields;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Content> contents = new ArrayList<>();

    public static Category of(String title, Member member) {
        Category category = new Category();
        category.setTitle(title);
        category.setMember(member);

        return category;
    }
}
