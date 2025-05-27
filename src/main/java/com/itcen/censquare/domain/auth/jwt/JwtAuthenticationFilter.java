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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

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

    String token = extractAccessTokenFromRequest(request);

    if (token != null && jwtProvider.validateToken(TokenType.ACCESS, token)) {
      String subject = jwtProvider.getSubject(TokenType.ACCESS, token);
      Long memberId = Long.valueOf(subject); // @Todo try catch 예외 발생

      CustomUserDetails userDetails = loadUserDetails(memberId);
      if (userDetails != null) {
        setAuthenticate(userDetails);
      }
    }

    filterChain.doFilter(request, response);

  }

  /**
   * Request로 부터 token 추출
   */
  private String extractAccessTokenFromRequest(HttpServletRequest request) {
    String header = request.getHeader(HEADER_AUTHORIZATION);
    if (header != null && header.startsWith(TOKEN_PREFIX)) {
      return header.substring(TOKEN_PREFIX.length());
    }

    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (AuthConstants.COOKIE_ACCESS_TOKEN.equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }

    return null;
  }

  private CustomUserDetails loadUserDetails(Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElse(null);
    if (member == null) {
      return null;
    }
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

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return path.startsWith("/oauth2");
  }

}
