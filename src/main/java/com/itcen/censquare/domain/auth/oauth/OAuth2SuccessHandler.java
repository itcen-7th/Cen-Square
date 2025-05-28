package com.itcen.censquare.domain.auth.oauth;


import com.itcen.censquare.domain.auth.AuthConstants;
import com.itcen.censquare.domain.auth.jwt.JwtProvider;
import com.itcen.censquare.domain.auth.jwt.TokenType;
import com.itcen.censquare.domain.auth.util.CookieUtil;
import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.member.entity.enums.Provider;
import com.itcen.censquare.domain.member.entity.enums.Role;
import com.itcen.censquare.domain.member.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private static final String REDIRECT_URL_AFTER_LOGIN = "/";
  private static final String OAUTH_ID_ATTRIBUTE = "id";
  public static final String ERROR_MISSING_OAUTH_ID = "OAuth 응답에 'id' 가 존재하지 않습니다.";

  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;
  private final StringRedisTemplate redisTemplate;

  public OAuth2SuccessHandler(JwtProvider jwtProvider, MemberRepository memberRepository,
      StringRedisTemplate redisTemplate) {
    this.jwtProvider = jwtProvider;
    this.memberRepository = memberRepository;
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    String oauthId = extractOAuth2Id(oAuth2User);

    Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttributes()
        .get("kakao_account");
    String email = kakaoAccount != null ? (String) kakaoAccount.get("email") : null;

    Member member = getOrCreateMember(oauthId, email);
    generateTokensAndSetCookies(response, String.valueOf(member.getMemberId()));

    response.sendRedirect(REDIRECT_URL_AFTER_LOGIN);
  }

  private static String extractOAuth2Id(OAuth2User oAuth2User) {
    return Optional.ofNullable(oAuth2User.getAttribute(OAUTH_ID_ATTRIBUTE))
        .map(String::valueOf)
        .orElseThrow(() -> new IllegalStateException(ERROR_MISSING_OAUTH_ID));
  }

  private Member getOrCreateMember(String oauthId, String email) {
    return memberRepository.findByOauthIdAndProvider(oauthId, Provider.KAKAO)
        .orElseGet(
            () -> memberRepository.save(
                Member.builder()
                    .oauthId(oauthId)
                    .provider(Provider.KAKAO)
                    .role(Role.USER)
                    .email(email)
                    .build()

            ))
        ;
  }

  /**
   * AccessToken, RefreshToken 생성, Redis 저장 및 쿠키 설정 수행
   *
   * @param response HTTP 응답 객체
   * @param memberId 인증된 사용자 ID
   */
  private void generateTokensAndSetCookies(HttpServletResponse response, String memberId) {
    String accessToken = jwtProvider.createToken(TokenType.ACCESS, memberId);
    String refreshToken = jwtProvider.createToken(TokenType.REFRESH, memberId);

    storeRefreshTokenInRedis(memberId, refreshToken);
    addTokenCookiesToResponse(response, accessToken, refreshToken);
  }

  private void storeRefreshTokenInRedis(String memberId, String refreshToken) {
    redisTemplate.opsForValue().set(
        AuthConstants.REDIS_REFRESH_TOKEN_PREFIX + memberId,
        refreshToken,
        Duration.ofDays(AuthConstants.REFRESH_TOKEN_EXPIRATION_DAYS)
    );
  }

  private void addTokenCookiesToResponse(HttpServletResponse response, String accessToken,
      String refreshToken) {
    ResponseCookie refreshTokenCookie = CookieUtil.createRefreshTokenCookie(refreshToken);
    ResponseCookie accessTokenCookie = CookieUtil.createAccessTokenCookie(accessToken);

    response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
    response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
  }

}

