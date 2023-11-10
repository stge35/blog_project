package com.blog_project.server.domain.category.service;

import com.blog_project.server.domain.category.dto.CategoryDto;
import com.blog_project.server.domain.category.entity.Category;
import com.blog_project.server.domain.category.repository.CategoryRepository;
import com.blog_project.server.domain.member.entity.Member;
import com.blog_project.server.domain.member.repository.MemberRepository;
import com.blog_project.server.global.error.exception.BusinessLogicException;
import com.blog_project.server.global.error.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    public CategoryService(CategoryRepository categoryRepository, MemberRepository memberRepository) {
        this.categoryRepository = categoryRepository;
        this.memberRepository = memberRepository;
    }

    // 생성
    @Transactional
    public void create(String title, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND, String.format("%s 을 찾을 수 없습니다.", email)));

        categoryRepository.save(Category.of(title, member));
    }

    // 수정
    @Transactional
    public CategoryDto update(String title, String email, Long categoryId) {

        Member member = memberRepository.findByEmail(email).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND, String.format("%을 찾을 수 없습니다.", email)));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND, String.format("%s 을 찾을 수 없습니다.", categoryId)));
        if(category.getMember() != member) {
            throw new BusinessLogicException(ExceptionCode.INVALID_PERMISSION, String.format("%s 는 %s 의 권환이 없습니다.", email, categoryId));
        }

        category.setTitle(title);

        return CategoryDto.from(categoryRepository.save(category));
    }

    // 삭제
    public void delete(String email, Long categoryId) {

        Member member = memberRepository.findByEmail(email).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND, String.format("%s 을 찾을 수 없습니다.", email)));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND, String.format("%s 을 찾을 수 없습니다.", categoryId)));
        if(!Objects.equals(category.getMember().getMemberId(), member.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.INVALID_PERMISSION, String.format("%s 는 권한이 없습니다.", email, categoryId));
        }

        categoryRepository.delete(category);
    }

    // 전체 조회
    public Page<CategoryDto> list(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryDto::from);
    }
}
