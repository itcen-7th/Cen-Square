package com.itcen.censquare.domain.member.service;

import com.itcen.censquare.domain.member.dto.MemberResDto;
import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberMapper memberMapper;

  public MemberResDto getMyInfo(Member member) {
    return memberMapper.toDto(member);
  }
}
