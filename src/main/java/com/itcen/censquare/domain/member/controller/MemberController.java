package com.itcen.censquare.domain.member.controller;

import com.itcen.censquare.domain.auth.AuthConstants;
import com.itcen.censquare.domain.auth.oauth.CustomUserDetails;
import com.itcen.censquare.domain.auth.util.CookieUtil;
import com.itcen.censquare.domain.member.dto.MemberResDto;
import com.itcen.censquare.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

  public static final String REDIRECT_AFTER_LOGOUT = "/";

  private final RedisTemplate<String, String> redisTemplate;
  private final MemberService memberService;

  public MemberController(RedisTemplate<String, String> redisTemplate,
      MemberService memberService) {
    this.redisTemplate = redisTemplate;
    this.memberService = memberService;
  }

  @PostMapping("/logout")
  public void logout(@AuthenticationPrincipal CustomUserDetails userDetails,
      HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    String memberId = String.valueOf(userDetails.getMember().getMemberId());

    redisTemplate.delete(AuthConstants.REDIS_REFRESH_TOKEN_PREFIX + memberId);

    ResponseCookie deleteAccessToken = CookieUtil.deleteAccessTokenCookie();
    ResponseCookie deleteRefreshToken = CookieUtil.deleteRefreshTokenCookie();
    response.addHeader(HttpHeaders.SET_COOKIE, deleteAccessToken.toString());
    response.addHeader(HttpHeaders.SET_COOKIE, deleteRefreshToken.toString());

    SecurityContextHolder.clearContext(); //  인증 정보 제거
    request.getSession().invalidate(); //  세션 무효화

    response.sendRedirect(REDIRECT_AFTER_LOGOUT);
  }

  @GetMapping("/me")
  public ResponseEntity<MemberResDto> getMyInfo(
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return ResponseEntity.ok(memberService.getMyInfo(userDetails.getMember()));
  }

}
