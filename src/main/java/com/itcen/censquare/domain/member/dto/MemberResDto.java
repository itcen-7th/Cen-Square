package com.itcen.censquare.domain.member.dto;

import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.member.entity.Role;
import lombok.Getter;

@Getter
public class MemberResDto {

  private final Long id;
  private final String provider;
  private final Role role;

  public MemberResDto(Member member) {
    this.id = member.getId();
    this.provider = member.getProvider();
    this.role = member.getRole();
  }

}
