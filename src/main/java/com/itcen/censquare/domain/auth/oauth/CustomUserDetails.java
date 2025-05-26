package com.itcen.censquare.domain.auth.oauth;

import com.itcen.censquare.domain.member.entity.Member;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class CustomUserDetails implements UserDetails {

  private final Member member;

  public CustomUserDetails(Member member) {
    this.member = member;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return Collections.singleton(new SimpleGrantedAuthority(member.getRole().getAuthority()));
  }

  /**
   * @return 소셜 로그인 경우 패스워드 사용X
   */
  @Override
  public String getPassword() {
    return null;
  }

  /**
   * @return 유니크한 식별자
   */
  @Override
  public String getUsername() {
    return member.getOauthId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
