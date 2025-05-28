package com.itcen.censquare.domain.workplace.repository;

import com.itcen.censquare.domain.workplace.entity.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

}
