package com.blog_project.server.domain.member.controller;

import com.blog_project.server.domain.member.dto.request.MemberPostDto;
import com.blog_project.server.domain.member.dto.response.MemberResponseDto;
import com.blog_project.server.domain.member.service.MemberService;
import com.blog_project.server.global.auth.dto.LoginDto;
import com.blog_project.server.global.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 가입
    @PostMapping
    public Response<MemberResponseDto> postMember(@RequestBody MemberPostDto request) {

        return Response.success(MemberResponseDto.from(memberService.addMember(request.getEmail(), request.getPassword())));
    }

    // 로그인
    @PostMapping("/login")
    public void login(@RequestBody LoginDto request){

    }

    // 회원 탈퇴
    @DeleteMapping("/withdrawal")
    public Response<Void> deleteMember(Authentication authentication) {
        memberService.delete(authentication.getName());
        return Response.success();
    }


}
