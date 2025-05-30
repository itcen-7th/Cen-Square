package com.itcen.censquare.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberExtraReqDto {

  @NotNull(message = "기수는 필수 항목입니다.")
  private Long batchNumber;

  @NotBlank(message = "닉네임을 입력해주세요.")
  @Size(max = 50, message = "닉네임은 50자 이내여야 합니다.")
  private String nickname;

  @NotBlank(message = "이름을 입력해주세요.")
  @Size(max = 50, message = "이름은 50자 이내여야 합니다.")
  private String name;

}
