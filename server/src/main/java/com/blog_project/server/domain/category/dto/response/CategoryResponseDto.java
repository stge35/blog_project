package com.blog_project.server.domain.category.dto.response;

import com.blog_project.server.domain.category.dto.CategoryDto;
import com.blog_project.server.domain.member.dto.response.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CategoryResponseDto {

    private Long categoryId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private MemberResponseDto member;

    public static CategoryResponseDto from(CategoryDto dto) {
        return new CategoryResponseDto(
                dto.getCategoryId(),
                dto.getTitle(),
                dto.getCreatedAt(),
                dto.getModifiedAt(),
                MemberResponseDto.from(dto.getMember())
        );
    }
}
