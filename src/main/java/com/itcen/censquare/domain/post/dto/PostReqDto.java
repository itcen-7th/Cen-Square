package com.itcen.censquare.domain.post.dto;


import com.itcen.censquare.domain.post.entity.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReqDto {

  @NotNull(message = "카테고리를 선택해주세요.")
  private Category category;

  @NotBlank(message = "제목을 입력해주세요.")
  @Size(max = 64, message = "제목은 64자 이내여야 합니다.")
  private String title;

  @NotBlank(message = "내용을 입력해주세요.")
  private String content;

}
