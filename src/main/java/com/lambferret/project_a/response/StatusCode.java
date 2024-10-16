package com.lambferret.project_a.response;

import org.springframework.http.HttpStatus;

public enum StatusCode {

    /**
     * HTTP 200 OK 정상처리
     */
    SUCCESS(HttpStatus.OK, "성공");


    public final HttpStatus httpStatus;
    public final String description;
    StatusCode(HttpStatus httpStatus, String description) {
        this.httpStatus = httpStatus;
        this.description = description;
    }
}
