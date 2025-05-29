package com.itcen.censquare.domain.member.service;

import com.itcen.censquare.domain.member.dto.MemberRespDto;
import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberMapper memberMapper;

  public MemberRespDto getMyInfo(Member member) {
    return memberMapper.toDto(member);
  }
}
