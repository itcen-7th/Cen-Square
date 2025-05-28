package com.itcen.censquare.domain.organization.entity;

import com.itcen.censquare.global.entity.TimeStampedEntity;
import com.itcen.censquare.global.entity.enums.Corporation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organization extends TimeStampedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "organization_id")
  private String organizationId;

  @Enumerated(EnumType.STRING)
  private Corporation corporation;

  private String division;

  private String department;
}
