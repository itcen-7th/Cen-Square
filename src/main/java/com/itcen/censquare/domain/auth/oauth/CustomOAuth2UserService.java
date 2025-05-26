package com.itcen.censquare.domain.auth.oauth;

import com.itcen.censquare.domain.member.entity.Role;
import java.util.Collections;
import java.util.Map;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

    // 사용자 정보 받아오기
    OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

    Map<String, Object> attributes = oAuth2User.getAttributes();

    return new DefaultOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority(Role.USER.getAuthority())),
        attributes,
        "id"  // id: OAuth2User.getName()으로 접근 가능
    );
  }
}
