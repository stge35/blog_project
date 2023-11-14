package com.blog_project.server.domain.content.entity;


import com.blog_project.server.domain.category.entity.Category;
import com.blog_project.server.domain.comment.entity.Comment;
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
public class Content extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;
    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public static Content of(String title, String body, Member member) {
        Content content = new Content();
        content.setTitle(title);
        content.setBody(body);
        content.setMember(member);

        return content;
    }
}
