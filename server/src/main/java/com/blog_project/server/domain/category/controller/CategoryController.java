package com.blog_project.server.domain.category.controller;

import com.blog_project.server.domain.category.dto.CategoryDto;
import com.blog_project.server.domain.category.dto.request.CategoryPatchDto;
import com.blog_project.server.domain.category.dto.request.CategoryPostDto;
import com.blog_project.server.domain.category.dto.response.CategoryResponseDto;
import com.blog_project.server.domain.category.service.CategoryService;
import com.blog_project.server.global.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 생성
    @PostMapping
    public Response<Void> create(@RequestBody CategoryPostDto request, Authentication authentication) {
        categoryService.create(request.getTitle(), authentication.getName());
        return Response.success();
    }

    // 수정
    @PatchMapping("/{category}")
    public Response<CategoryResponseDto> update(@PathVariable Long categoryId,
                                                @RequestBody CategoryPatchDto request,
                                                Authentication authentication) {
        CategoryDto categoryDto = categoryService.update(request.getTitle(),authentication.getName(),categoryId);

        return Response.success(CategoryResponseDto.from(categoryDto));
    }

    // 삭제
    @DeleteMapping("/{categoryId}")
    public Response<Void> delete(@PathVariable Long categoryId, Authentication authentication) {
        categoryService.delete(authentication.getName(), categoryId);

        return Response.success();
    }

    //전체 검색
    @GetMapping
    public Response<Page<CategoryResponseDto>> list(Pageable pageable) {
        return Response.success(categoryService.list(pageable).map(CategoryResponseDto::from));
    }
}
