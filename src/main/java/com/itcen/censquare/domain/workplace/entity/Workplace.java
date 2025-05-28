package com.itcen.censquare.domain.workplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workplace")
@Getter
@Setter
@NoArgsConstructor
public class Workplace {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "work_place_id")
  private Long id;

  @Column(name = "corporation_name", nullable = false)
  private String corporationName;

  @Column(name = "office_location", nullable = false)
  private String officeLocation;

  @Column(name = "division_name")
  private String divisionName;

  private Double latitude;

  private Double longitude;

  @Column(name = "Field")
  private String field;

  @Column(name = "Field2")
  private String field2;
}
