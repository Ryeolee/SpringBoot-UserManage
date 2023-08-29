package com.user.usermanage.user.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.usermanage.user.Exception.Constants;
import com.user.usermanage.user.Exception.CustomException;
import lombok.SneakyThrows;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse,
                                    FilterChain filterChain) {

        String token = jwtTokenProvider.resolveAccessToken(servletRequest);

        LOGGER.info(token);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                LOGGER.info("[doFilterInternal] token 값 유효성 체크 완료");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            LOGGER.info("[do1111");
            // 에러 응답을 생성
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", 417);
            errorResponse.put("message", "만료된 jwt 토큰입니다.");

            // JSON으로 변환하여 응답 보내기
            PrintWriter out = httpServletResponse.getWriter();
            objectMapper.writeValue(out, errorResponse);
            out.flush();
        }
    }

}

