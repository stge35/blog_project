package com.blog_project.server.domain.member.controller;

import com.blog_project.server.domain.member.dto.request.MemberPostDto;
import com.blog_project.server.domain.member.dto.response.MemberResponseDto;
import com.blog_project.server.domain.member.service.MemberService;
import com.blog_project.server.global.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Response<MemberResponseDto> postMember(@RequestBody MemberPostDto request) {

        return Response.success(MemberResponseDto.from(memberService.addMember(request.getEmail(), request.getPassword())));
    }

}
