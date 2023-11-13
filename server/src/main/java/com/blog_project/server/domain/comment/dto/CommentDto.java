package com.blog_project.server.domain.comment.dto;

import com.blog_project.server.domain.comment.entity.Comment;
import com.blog_project.server.domain.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {

    private Long commentId;
    private String body;
    private Long contentId;
    private MemberDto member;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static CommentDto from(Comment entity){
        return new CommentDto(
                entity.getCommentId(),
                entity.getBody(),
                entity.getContent().getContentId(),
                MemberDto.from(entity.getMember()),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
