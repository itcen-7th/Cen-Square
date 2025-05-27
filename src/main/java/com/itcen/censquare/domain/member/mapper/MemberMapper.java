package com.itcen.censquare.domain.member.mapper;

import com.itcen.censquare.domain.member.dto.MemberResDto;
import com.itcen.censquare.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

  MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

  MemberResDto toDto(Member member);
}
