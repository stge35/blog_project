package com.blog_project.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    public static Response<Void> success() {

        return new Response<Void>("Success", null);
    }

    public static<T> Response<T> success(T result) {

        return new Response<>("SUCCESS", result);
    }

    public static <T> Response<Void> error(String errorCode) {

        return new Response<>(errorCode, null);
    }
}
