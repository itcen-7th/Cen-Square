package com.itcen.censquare.domain.member.dto;

import com.itcen.censquare.domain.member.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRespDto {

  private Long memberId;
  private Role role;
  private String oauthId;
  private Long batchNumber;
  private String nickname;
  private String name;
  private String email;

}
