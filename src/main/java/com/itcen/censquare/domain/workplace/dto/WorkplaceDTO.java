package com.itcen.censquare.domain.workplace.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkplaceDTO {

  private Long id;
  private String corporationName;
  private String officeLocation;
  private String divisionName;
  private Double latitude;
  private Double longitude;
  private String field; // 지역_시도
  private String field2; // 지역_구군
}
