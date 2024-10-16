package com.lambferret.project_a.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiResponse {
    private String code;
    private String message;
    private Object data;

    public ApiResponse(StatusCode value) {
        this.code = value.name();
        this.message = value.description;
    }

    public ApiResponse(StatusCode value, Object data) {
        this.code = value.name();
        this.message = value.description;
        this.data = data;
    }
}
