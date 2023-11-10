package com.blog_project.server.domain.member.service;

import com.blog_project.server.domain.member.dto.MemberDto;
import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto addMember(String email, String password) {
        Member savedMember = memberRepository.save(Member.of(email, password));

        return MemberDto.from(savedMember);
    }
}
