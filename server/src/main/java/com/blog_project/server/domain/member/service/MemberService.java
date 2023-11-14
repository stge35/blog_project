package com.blog_project.server.domain.member.service;

import com.blog_project.server.domain.category.dto.response.CategoryResponseDto;
import com.blog_project.server.domain.category.repository.CategoryRepository;
import com.blog_project.server.domain.member.dto.MemberDto;
import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.domain.member.repository.MemberRepository;
import com.blog_project.server.global.auth.utils.CustomAuthorityUtils;
import com.blog_project.server.global.error.exception.BusinessLogicException;
import com.blog_project.server.global.error.exception.ExceptionCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // 회원 가
    public MemberDto addMember(String email, String password) {
        memberRepository.findByEmail(email).ifPresent(it ->{
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS, String.format("%s 는 존재합니다.", email));
        });
        Member savedMember = memberRepository.save(Member.of(email, passwordEncoder.encode(password)));
        List<String> roles =customAuthorityUtils.createRoles(email);
        savedMember.setRoles(roles);

        return MemberDto.from(savedMember);
    }

    // 회원 삭제
    public void delete(String email) {
        Member member = memberOrException(email);
        memberRepository.delete(member);
    }

    private Member memberOrException(String email) {
        return memberRepository.findByEmail(email).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND, String.format("%s 를 찾을 수 없습니다.", email)));
    }
}
