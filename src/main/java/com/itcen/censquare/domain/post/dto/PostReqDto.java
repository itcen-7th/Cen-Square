package com.itcen.censquare.domain.post.dto;


import com.itcen.censquare.domain.post.entity.enums.Category;
import lombok.Getter;

@Getter
public class PostReqDto {

  private Long memberId;
  private Category category;
  private String title;
  private String content;
}
