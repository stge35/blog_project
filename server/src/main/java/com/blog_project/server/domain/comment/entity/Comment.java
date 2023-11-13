package com.blog_project.server.domain.comment.entity;

import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.global.audit.AuditingFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Comment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(columnDefinition = "TEXT", length = 5000)
    private String body;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "content_id")
    private Content content;

    public static Comment of(Member member, Content content, String body) {
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setContent(content);
        comment.setBody(body);

        return comment;
    }
}
