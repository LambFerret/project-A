package com.lambferret.project_a.controller;

import com.lambferret.project_a.dto.LoginDto;
import com.lambferret.project_a.response.ApiResponse;
import com.lambferret.project_a.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> handleLogin(@RequestBody LoginDto loginRequest) {
        log.info("handleLogin |  🐳  | " + loginRequest);
        if (true) {
            return ResponseEntity.ok(new ApiResponse(StatusCode.SUCCESS));
        } else {
            throw new RuntimeException("로그인 실패");
        }
    }
}
