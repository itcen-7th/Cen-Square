package com.itcen.censquare.domain.post.mapper;

import com.itcen.censquare.domain.post.dto.PostReqDto;
import com.itcen.censquare.domain.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

//  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  @Mapping(target = "viewCount", constant = "0L")
  @Mapping(target = "likeCount", constant = "0L")
  @Mapping(target = "deleted", constant = "false")
  Post toEntity(PostReqDto dto);

}
