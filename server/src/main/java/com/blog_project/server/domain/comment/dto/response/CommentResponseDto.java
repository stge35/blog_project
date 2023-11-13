package com.blog_project.server.domain.comment.dto.response;

import com.blog_project.server.domain.comment.dto.CommentDto;
import com.blog_project.server.domain.member.dto.response.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private Long contentId;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private MemberResponseDto member;

    public static CommentResponseDto from(CommentDto dto) {
        return new CommentResponseDto(
                dto.getCommentId(),
                dto.getContentId(),
                dto.getBody(),
                dto.getCreatedAt(),
                dto.getModifiedAt(),
                MemberResponseDto.from(dto.getMember())
        );
    }
}
