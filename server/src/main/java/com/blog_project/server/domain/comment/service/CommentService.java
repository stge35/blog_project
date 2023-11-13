package com.blog_project.server.domain.comment.service;

import com.blog_project.server.domain.comment.dto.CommentDto;
import com.blog_project.server.domain.comment.entity.Comment;
import com.blog_project.server.domain.comment.repository.CommentRepository;
import com.blog_project.server.domain.content.entity.Content;
import com.blog_project.server.domain.content.repository.ContentRepository;
import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.domain.member.repository.MemberRepository;
import com.blog_project.server.global.error.exception.BusinessLogicException;
import com.blog_project.server.global.error.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public CommentService(ContentRepository contentRepository, CommentRepository commentRepository, MemberRepository memberRepository) {
        this.contentRepository = contentRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }

    // 생성
    @Transactional
    public void create(Long contentId, String email, String body) {
        Content content = contentOrException(contentId);
        Member member = memberOrException(email);
        commentRepository.save(Comment.of(member, content, body));
    }

    // 전체 조회
    @Transactional
    public Page<CommentDto> list(Long contentId, Pageable pageable) {
        Content content = contentOrException(contentId);
        return commentRepository.findAllByContent(content, pageable).map(CommentDto::from);
    }

    private Content contentOrException(Long contentId) {
        return contentRepository.findById(contentId).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.CONTENT_NOT_FOUNT, String.format("%s 게시글을 찾을 수 없습니다.",contentId)));
    }

    private Member memberOrException(String email) {
        return memberRepository.findByEmail(email).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND, String.format("%s 멤버를 찾을 수 없습니다.", email)));
    }
}
