package com.itcen.censquare.domain.auth.util;

import com.itcen.censquare.domain.auth.AuthConstants;
import java.time.Duration;
import org.springframework.http.ResponseCookie;

public class CookieUtil {

  private static final boolean SECURE = false;
  private static final String PATH = "/";
  private static final String SAME_SITE = "Lax";

  public static ResponseCookie createAccessTokenCookie(String token) {
    return createTokenCookie(AuthConstants.COOKIE_ACCESS_TOKEN, token,
        Duration.ofMinutes(AuthConstants.ACCESS_TOKEN_EXPIRATION_MINUTES));
  }

  public static ResponseCookie createRefreshTokenCookie(String token) {
    return createTokenCookie(AuthConstants.COOKIE_REFRESH_TOKEN, token,
        Duration.ofDays(AuthConstants.REFRESH_TOKEN_EXPIRATION_DAYS));
  }

  public static ResponseCookie deleteAccessTokenCookie() {
    return createTokenCookie(AuthConstants.COOKIE_ACCESS_TOKEN, "", Duration.ZERO);
  }

  public static ResponseCookie deleteRefreshTokenCookie() {
    return createTokenCookie(AuthConstants.COOKIE_REFRESH_TOKEN, "", Duration.ZERO);
  }

  /**
   * 토큰 쿠기 생성 메서드
   *
   * @param tokenType  쿠키 이름 AccessToken/RefreshToken
   * @param tokenValue 쿠키 값
   * @param duration   쿠키 만료 시간
   * @return ResponseCookie 객체
   */
  private static ResponseCookie createTokenCookie(String tokenType, String tokenValue,
      Duration duration) {
    return ResponseCookie.from(tokenType, tokenValue)
        .httpOnly(true)
        .secure(SECURE)
        .path(PATH)
        .sameSite(SAME_SITE)
        .maxAge(duration)
        .build();
  }
}
