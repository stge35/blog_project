package com.blog_project.server.domain.member.dto.response;

import com.blog_project.server.domain.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private Long memberId;
    private String email;
    private LocalDateTime createdAt;

    public static MemberResponseDto from(MemberDto dto) {

        return new MemberResponseDto(
                dto.getMemberId(),
                dto.getEmail(),
                dto.getCreatedAt()
        );
    }
}
