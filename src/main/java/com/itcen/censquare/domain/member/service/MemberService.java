package com.itcen.censquare.domain.member.service;

import com.itcen.censquare.domain.member.dto.MemberResDto;
import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.member.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private final MemberMapper memberMapper;

  public MemberService(MemberMapper memberMapper) {
    this.memberMapper = memberMapper;
  }

  public MemberResDto getMyInfo(Member member) {
    return memberMapper.toDto(member);
  }
}
