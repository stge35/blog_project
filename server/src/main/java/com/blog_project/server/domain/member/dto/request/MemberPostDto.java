package com.blog_project.server.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberPostDto {

    private String email;
    private String password;
}
