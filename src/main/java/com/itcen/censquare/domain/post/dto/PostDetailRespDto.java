package com.itcen.censquare.domain.post.dto;

import com.itcen.censquare.domain.post.entity.enums.Category;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDetailRespDto {

  private Long postId;
  private String title;
  private String content;
  private String nickname;
  private Category category;
  private Long viewCount;
  private Long likeCount;
  private LocalDateTime createdAt;
}
