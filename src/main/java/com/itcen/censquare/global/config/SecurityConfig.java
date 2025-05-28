package com.itcen.censquare.global.config;

import com.itcen.censquare.domain.auth.jwt.JwtAuthenticationFilter;
import com.itcen.censquare.domain.auth.jwt.JwtProvider;
import com.itcen.censquare.domain.auth.oauth.CustomOAuth2UserService;
import com.itcen.censquare.domain.auth.oauth.OAuth2SuccessHandler;
import com.itcen.censquare.domain.member.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;

  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2SuccessHandler oAuth2SuccessHandler;

  public SecurityConfig(JwtProvider jwtProvider, MemberRepository memberRepository,
      CustomOAuth2UserService customOAuth2UserService, OAuth2SuccessHandler oAuth2SuccessHandler) {
    this.jwtProvider = jwtProvider;
    this.memberRepository = memberRepository;
    this.customOAuth2UserService = customOAuth2UserService;
    this.oAuth2SuccessHandler = oAuth2SuccessHandler;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/favicon.ico", "/login/**", "/oauth2/**", "/js/**", "/css/**",
                "/img/**")
            .permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll()
        )
        .oauth2Login(oauth2 -> oauth2
            .loginPage("/oauth2/authorization/kakao") // 인증 필요시 자동 리다이렉트
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error=true")
            .userInfoEndpoint(user -> user.userService(customOAuth2UserService))
            .successHandler(oAuth2SuccessHandler)
        )
        .addFilterBefore(
            new JwtAuthenticationFilter(jwtProvider, memberRepository),
            UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
