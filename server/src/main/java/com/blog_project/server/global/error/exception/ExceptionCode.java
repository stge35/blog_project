package com.blog_project.server.global.error.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "멤버를 찾을 수 없습니다."),
    MEMBER_EXISTS(409, "이미 존재하는 멤버 입니다.."),
    CATEGORY_NOT_FOUND(404, "카테고리를 찾을 수 없습니다."),
    CONTENT_NOT_FOUNT(404, "게시글을 찾을 수 없습니다."),
    INVALID_PERMISSION(403, "권한이 없습니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류");

    private int status;
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
