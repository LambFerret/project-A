package com.lambferret.project_a.controller;

import com.lambferret.project_a.document.User;
import com.lambferret.project_a.dto.LoginDto;
import com.lambferret.project_a.response.ApiResponse;
import com.lambferret.project_a.response.StatusCode;
import com.lambferret.project_a.security.core.JwtUtil;
import com.lambferret.project_a.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> handleLogin(@RequestBody LoginDto loginRequest) {
        // 비밀번호를 이용해 사용자 조회
        User user = userService.findByCompanyName(loginRequest.getPassword());

        if (user != null) {
            // 회사명을 JWT의 subject로 설정해 토큰 생성
            String jwt = JwtUtil.createToken(user.getCompanyName());
            log.info("JWT: {}", jwt);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + jwt)
                    .body(new ApiResponse(StatusCode.SUCCESS));
        } else {
            throw new RuntimeException("Invalid password");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile(Authentication authentication) {
        String username = authentication.getName();

        log.info("username: {}", username);
        return ResponseEntity.ok()
                .body(new ApiResponse(StatusCode.SUCCESS, username));
    }

}
