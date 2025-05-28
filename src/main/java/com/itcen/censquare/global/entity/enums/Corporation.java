package com.itcen.censquare.global.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Corporation {
  ITCEN_ENTEC("아이티센엔텍"),
  ITCEN_GLOBAL("아이티센글로벌"),
  ITCEN_CLOIT("아이티센클로잇"),
  ITCEN_CORE("아이티센코어"),
  ITCEN_CTS("아이티센씨티에스"),
  ITCEN_PNS("아이티센피엔에스"),
  ITCEN_CPLATFORM("아이티센씨플랫폼");

  private final String text;
}
