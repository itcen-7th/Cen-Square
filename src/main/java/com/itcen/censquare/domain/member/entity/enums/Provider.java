package com.itcen.censquare.domain.member.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {
  KAKAO("kakao");

  private final String text;
}
