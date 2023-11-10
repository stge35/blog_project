package com.blog_project.server.domain.member.service;

import com.blog_project.server.domain.category.dto.response.CategoryResponseDto;
import com.blog_project.server.domain.category.repository.CategoryRepository;
import com.blog_project.server.domain.member.dto.MemberDto;
import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.domain.member.repository.MemberRepository;
import com.blog_project.server.global.auth.utils.CustomAuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils customAuthorityUtils;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils customAuthorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.customAuthorityUtils = customAuthorityUtils;
    }

    public MemberDto addMember(String email, String password) {
        Member savedMember = memberRepository.save(Member.of(email, password));

        return MemberDto.from(savedMember);
    }
}
