package com.itcen.censquare.domain.auth.jwt;

import com.itcen.censquare.domain.auth.AuthConstants;
import com.itcen.censquare.domain.auth.oauth.CustomUserDetails;
import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  public static final String ERROR_USER_NOT_FOUND = "로그인 사용자를 찾을 수 없습니다. memberId: ";
  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";

  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;

  public JwtAuthenticationFilter(JwtProvider jwtProvider, MemberRepository memberRepository) {
    this.jwtProvider = jwtProvider;
    this.memberRepository = memberRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    extractAccessTokenFromRequest(request)
        .filter(token -> jwtProvider.validateToken(TokenType.ACCESS, token))
        .map(token -> jwtProvider.getSubject(TokenType.ACCESS, token))
        .map(Long::valueOf)
        .map(this::loadUserDetails)
        .ifPresent(this::setAuthenticate);

    filterChain.doFilter(request, response);
  }

  /**
   * Request로 부터 token 추출
   */
  private Optional<String> extractAccessTokenFromRequest(HttpServletRequest request) {
    String header = request.getHeader(HEADER_AUTHORIZATION);
    if (header != null && header.startsWith(TOKEN_PREFIX)) {
      return Optional.of(header.substring(TOKEN_PREFIX.length()));
    }

    return Optional.ofNullable(request.getCookies())
        .flatMap(cookies -> Arrays.stream(cookies)
            .filter(cookie -> AuthConstants.COOKIE_ACCESS_TOKEN.equals(cookie.getName()))
            .map(Cookie::getValue)
            .findFirst()
        );
  }

  private CustomUserDetails loadUserDetails(Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new RuntimeException(ERROR_USER_NOT_FOUND + memberId));
    return new CustomUserDetails(member);
  }

  private void setAuthenticate(CustomUserDetails userDetails) {
    // 유저 인증 객체 생성
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

}
