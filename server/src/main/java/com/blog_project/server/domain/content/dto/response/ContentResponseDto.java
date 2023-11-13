package com.blog_project.server.domain.content.dto.response;

import com.blog_project.server.domain.content.dto.ContentDto;
import com.blog_project.server.domain.member.dto.response.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ContentResponseDto {

    private Long contentId;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private MemberResponseDto member;

    public static ContentResponseDto from(ContentDto dto) {
        return new ContentResponseDto(
                dto.getContentId(),
                dto.getTitle(),
                dto.getBody(),
                dto.getCreatedAt(),
                dto.getModifiedAt(),
                MemberResponseDto.from(dto.getMember())
        );
    }
}
