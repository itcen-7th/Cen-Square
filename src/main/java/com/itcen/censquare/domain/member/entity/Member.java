package com.itcen.censquare.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String oauthId;

  private String provider;
  
  @Enumerated(EnumType.STRING)
  private Role role;

  public Member() {

  }

  public Member(String oauthId, String provider, Role role) {
    this.oauthId = oauthId;
    this.provider = provider;
    this.role = role;
  }

}
