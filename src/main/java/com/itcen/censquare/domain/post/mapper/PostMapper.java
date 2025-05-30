package com.itcen.censquare.domain.post.mapper;

import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.post.dto.PostDetailRespDto;
import com.itcen.censquare.domain.post.dto.PostListRespDto;
import com.itcen.censquare.domain.post.dto.PostReqDto;
import com.itcen.censquare.domain.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

  @Mapping(target = "viewCount", constant = "0L")
  @Mapping(target = "likeCount", constant = "0L")
  @Mapping(target = "deleted", constant = "false")
  @Mapping(target = "member", source = "member")
  Post toEntity(PostReqDto dto, Member member);

  @Mapping(target = "nickname", source = "member.nickname")
  PostListRespDto toDto(Post post);

  @Mapping(target = "nickname", source = "member.nickname")
  PostDetailRespDto toDetailDto(Post post);
}
