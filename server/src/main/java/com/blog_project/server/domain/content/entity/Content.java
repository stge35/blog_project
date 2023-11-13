package com.blog_project.server.domain.content.entity;


import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.global.audit.AuditingFields;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
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

    public static Content of(String title, String body, Member member) {
        Content content = new Content();
        content.setTitle(title);
        content.setBody(body);
        content.setMember(member);

        return content;
    }
}
