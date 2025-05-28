package com.itcen.censquare.domain.organization.entity;

import com.itcen.censquare.global.entity.TimeStampedEntity;
import com.itcen.censquare.global.entity.enums.Corporation;
import jakarta.persistence.*;
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
