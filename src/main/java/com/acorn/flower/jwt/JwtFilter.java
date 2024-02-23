package com.acorn.flower.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.acorn.flower.user.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//요청할때마다 한번 걸치는 필터 이다.(스프링 프레임워크 내에서 동작하는 필터)
@Component
public class JwtFilter extends OncePerRequestFilter {

	// jwt 토큰얻기
	@Value("${jwt.name}")
	private String jwtName;
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// client 가 요청 헤더에 담은 정보를 얻어낸다.
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String id = null;

		// 인증 Header 가 존재하고 해당 문자열이 Bearer 로 시작하는지 확인
		if (authHeader != null && authHeader.startsWith("Bearer+")) {
			// 앞에 "Bearer " 를 제외한 순수 토큰 문자열 얻어내기
			token = authHeader.substring(7);
			// JwtUtil 를 이용해서 토큰에 저장된 userName (Subject) 를 얻어낸다.
			id = jwtUtil.extractUsername(token);
		}

		// userName이 존재하고 Spring Security 에서 아직 인증을 받지 않은 상태라면
		if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// DB 에서 UserDetails 객체를 얻어내서
			UserDetails userDetails = service.loadUserByUsername(id);
			// 토큰이 유효한 토큰인지 유틸을 이용해서 알아낸 다음
			boolean isValid = jwtUtil.validateToken(token, userDetails);
			// 만일 유효 하다면 1회성 로그인 처리를 한다.
			if (isValid) {
				// 사용자가 제출한 사용자 이름과 비밀번호와 같은 인증 자격 증명을 저장
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// Security 컨텍스트 업데이트 (1회서 로그인)
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		// 다음 spirng filter chain 진행하기
		filterChain.doFilter(request, response);
	}

}