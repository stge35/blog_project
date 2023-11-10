package com.blog_project.server.global.error.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "멤버를 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(404, "카테고리를 찾을 수 없습니다."),
    INVALID_PERMISSION(403, "권한이 없습니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류");

    private int status;
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
