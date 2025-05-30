package com.itcen.censquare.domain.member.service;

import com.itcen.censquare.domain.member.dto.MemberExtraReqDto;
import com.itcen.censquare.domain.member.dto.MemberRespDto;
import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.member.entity.enums.State;
import com.itcen.censquare.domain.member.mapper.MemberMapper;
import com.itcen.censquare.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

  public static final String ERROR_NOT_FOUND_MEMBER = "Member 찾을 수 없음 ";
  private final MemberMapper memberMapper;
  private final MemberRepository memberRepository;

  public MemberRespDto getMyInfo(Member member) {
    return memberMapper.toDto(member);
  }

  public void saveExtraInfo(Member member, MemberExtraReqDto memberExtraReqDto) {
    Member foundMember = memberRepository.findById(member.getMemberId())
        .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_FOUND_MEMBER));
    foundMember.updateExtraInfo(memberExtraReqDto.getBatchNumber(), memberExtraReqDto.getNickname(),
        memberExtraReqDto.getName());
    foundMember.changeState(State.COMPLETED);
  }
}
