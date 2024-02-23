package com.acorn.flower.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.acorn.flower.filter.JwtFilter;

@Configuration //설정 클래스라고 알려준다
@EnableWebSecurity //Security 를 설정하기 위한 어노테이션
public class SecurityConfig {
	
	//jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired private DataSource dataSource;
	
	@Bean //메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		//화이트 리스트를 미리 배열에 넣어두기
		String[] whiteList= {"/", "/auth",			
				"/api/**", "/upload/**", 
				"/test/**","/css/**","/img/**","/js/**",
				"/scss/**","/vendor/**","/main/**",
				"/index.html", "/static/**","/test"};

		//메소드의 매개변수에 HttpSecurity 의 참조값이 전달되는데 해당 객체를 이용해서 설정을 한다음
		httpSecurity
		
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(config -> 
			config
				.requestMatchers(whiteList).permitAll() //whiteList 요청은 로그인과 상관없이 모두 허용
				.requestMatchers("/super/**").hasRole("super") //슈퍼계정
				.requestMatchers("/owner/**").hasAnyRole("owner","super")  //사장
				.requestMatchers("/emp/**").hasAnyRole("owner","emp","super")  //사장+사원
				.anyRequest().authenticated()
		)
		.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		//토큰을 검사하는 필터를 security filter 가 동작하기 이전에 동작하도록 설정 한다.
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		//설정된 정보대로 SecurityFilterChain 객체를 만들어서 반환한다 
		return httpSecurity.build();
	}
	
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
	@Bean
	PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	//인증 메니저 객체를 bean 으로 만든다. (Spring Security 가 자동 로그인 처리할때도 사용되는 객체)
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, 
			BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) throws Exception {
	    
		return http.getSharedObject(AuthenticationManagerBuilder.class) 
	      .userDetailsService(userDetailService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
	@Bean
	public PersistentTokenRepository persitstentTokenRepository() {
		JdbcTokenRepositoryImpl repo=new JdbcTokenRepositoryImpl(); //사용자의 로그인 토큰을 저장하고 관리하는 인터페이스(Security에서 제공해준다)
		repo.setDataSource(dataSource); //매소드를 통해 yml에 설정한 db와 연동
		return repo;
	}
	
}
