package com.itcen.censquare.domain.auth.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private final String accessSecret;
  private final String refreshSecret;

  private final Map<TokenType, Long> EXPIRATIONS = Map.of(
      TokenType.ACCESS, 15 * 60 * 1000L, // 15분
      TokenType.REFRESH, 7 * 24 * 60 * 1000L // 7일
  );

  private final Map<TokenType, Key> keys = new EnumMap<>(TokenType.class);

  public JwtProvider(@Value("${jwt.secret.access}") String accessSecret,
      @Value("${jwt.secret.refresh}") String refreshSecret) {
    this.accessSecret = accessSecret;
    this.refreshSecret = refreshSecret;

    keys.put(TokenType.ACCESS, Keys.hmacShaKeyFor(accessSecret.getBytes()));
    keys.put(TokenType.REFRESH, Keys.hmacShaKeyFor(refreshSecret.getBytes()));
  }

  /**
   * 토큰 생성 (Access/Refresh)
   *
   * @param memberId 회원 ID
   * @return 생성된 JWT 문자열
   */
  public String createToken(TokenType type, String memberId) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + EXPIRATIONS.get(type));

    return Jwts.builder()
        .setSubject(memberId)
        .setIssuedAt(now)
        .setExpiration(expiry)
        .signWith(keys.get(type), SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * 토큰 검증 메소드
   *
   * @param token 토큰
   * @return true/false
   */
  public boolean validateToken(TokenType type, String token) {
    try {
      getParser(type).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * 토큰에서 회원 ID(subject) 추출
   *
   * @param type  토큰 타입 (ACCESS / REFRESH)
   * @param token JWT 문자열
   * @return 회원 ID (memberId)
   */
  public String getSubject(TokenType type, String token) {
    return getParser(type)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  private JwtParser getParser(TokenType type) {
    return Jwts.parserBuilder()
        .setSigningKey(keys.get(type))
        .build();
  }

}
