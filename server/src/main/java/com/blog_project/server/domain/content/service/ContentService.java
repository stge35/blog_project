package com.blog_project.server.domain.content.service;

import com.blog_project.server.domain.category.repository.CategoryRepository;
import com.blog_project.server.domain.content.dto.ContentDto;
import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.content.repository.ContentRepository;
import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.domain.member.repository.MemberRepository;
import com.blog_project.server.global.error.exception.BusinessLogicException;
import com.blog_project.server.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final MemberRepository memberRepository;

    public ContentService(ContentRepository contentRepository, MemberRepository memberRepository) {
        this.contentRepository = contentRepository;
        this.memberRepository = memberRepository;
    }

    // 생성
    @Transactional
    public void create(String title, String body, String email) {

        Member member = memberOrException(email);
        contentRepository.save(Content.of(title, body, member));
    }

    // 수정
    @Transactional
    public ContentDto update(String title, String body, String email, Long contentId) {

        Member member = memberOrException(email);
        Content content = contentOrException(contentId);
        content.setTitle(title);
        content.setBody(body);

        return ContentDto.from(contentRepository.save(content));
    }

    // 삭제
    public void delete(String email, Long contentId) {
        Member member = memberOrException(email);
        Content content = contentOrException(contentId);
        contentRepository.delete(content);
    }

    // 단건 조회
    @Transactional
    public ContentDto findById(Long contentId) {

        Content content = contentOrException(contentId);
        return ContentDto.from(content);
    }

    // 전체 조회

    private Content contentOrException(Long contentId) {

        return contentRepository.findById(contentId).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.CONTENT_NOT_FOUNT, String.format("%s 번의 게시글이 존재하지 않습니다.", contentId)));
    }

    private Member memberOrException(String email) {

        return memberRepository.findByEmail(email).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND, String.format("%s 를 찾을 수 없습니다.", email)));
    }
}
