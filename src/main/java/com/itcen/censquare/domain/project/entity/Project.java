package com.itcen.censquare.domain.project.entity;

import com.itcen.censquare.domain.organization.entity.Organization;
import com.itcen.censquare.domain.workplace.entity.WorkPlace;
import com.itcen.censquare.global.entity.TimeStampedEntity;
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
public class Project extends TimeStampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private String projectId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "work_place_id", nullable = false)
    private WorkPlace workPlace;
}
