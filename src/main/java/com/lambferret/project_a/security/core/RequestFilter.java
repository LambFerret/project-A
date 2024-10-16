package com.lambferret.project_a.security.core;

import com.lambferret.project_a.document.User;
import com.lambferret.project_a.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

    private final UserService userService;

    public RequestFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals("/login") || path.equals("/register");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {

        var requestWrapper = new ContentCachingRequestWrapper(request);
        var responseWrapper = new ContentCachingResponseWrapper(response);

        try {
            final String requestHeader = request.getHeader("Authorization");

            String jwt = null;
            String companyName = null;

            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                jwt = requestHeader.substring(7);
                companyName = JwtUtil.getSubject(jwt); // JWT에서 companyName 추출
            }
            // 테스트를 위해 모든 요청에 대해 Authentication 객체를 생성합니다.
            User mockUser = User.builder()
                    .companyName(companyName)
                    .password("password123")
                    .build();

            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    mockUser, null, null);  // 권한은 null로 설정

            SecurityContextHolder.getContext().setAuthentication(authToken);

            // 원래 JWT 및 사용자 검증 부분 - 필요 시 복원
            /*

            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                jwt = requestHeader.substring(7);
                companyName = JwtUtil.getSubject(jwt); // JWT에서 companyName 추출
            }

            if (companyName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userService.findByCompanyName(companyName);

                if (user != null && JwtUtil.isTokenValid(jwt, companyName)) {
                    Authentication authToken = new UsernamePasswordAuthenticationToken(
                            user, null, null); // 권한이 없는 경우 null로 설정
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    responseWrapper.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            "Invalid JWT token or user not found");
                    return;
                }
            }
            */

            // 필터 체인 진행
            filterChain.doFilter(requestWrapper, responseWrapper);

        } catch (Exception e) {
            responseWrapper.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication error");
            return;
        } finally {
            // 응답 내용을 클라이언트에 전달
            responseWrapper.copyBodyToResponse();
        }
    }
}