package com.blog_project.server.domain.member.dto;

import com.blog_project.server.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MemberDto {

    private Long memberId;
    private String email;
    private LocalDateTime createdAt;



    public static MemberDto from(Member entity) {

        return new MemberDto(
                entity.getMemberId(),
                entity.getEmail(),
                entity.getCreatedAt()
        );
    }
}
