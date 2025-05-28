package com.itcen.censquare.domain.workplace.entity;

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
public class WorkPlace extends TimeStampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_place_id")
    private Long workPlaceId;

    @Enumerated(EnumType.STRING)
    private Corporation corporation;

    @Column(nullable = false, length = 50)
    private String officeLocation;

    @Column(length = 50)
    private String divisionName;

    private Double latitude;

    private Double longitude;

    @Column(length = 50)
    private String sido;

    @Column(length = 50)
    private String gungu;
}
