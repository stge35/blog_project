package com.blog_project.server.domain.content.dto;

import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ContentDto {

    private Long contentId;
    private String title;
    private String body;
    private MemberDto member;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ContentDto from(Content entity) {
        return new ContentDto(
                entity.getContentId(),
                entity.getTitle(),
                entity.getBody(),
                MemberDto.from(entity.getMember()),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
