package com.blog_project.server.domain.category.dto;

import com.blog_project.server.domain.category.entity.Category;
import com.blog_project.server.domain.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {

    private Long categoryId;
    private String title;
    private MemberDto member;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static CategoryDto from(Category entity) {
        return new CategoryDto(
                entity.getCategoryId(),
                entity.getTitle(),
                MemberDto.from(entity.getMember()),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
