package com.itcen.censquare.domain.member.dto;

import com.itcen.censquare.domain.member.entity.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberResDto {

  private final Long id;
  private final String provider;
  private final Role role;

}
