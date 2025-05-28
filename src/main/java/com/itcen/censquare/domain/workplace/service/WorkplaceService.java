package com.itcen.censquare.domain.workplace.service;

import com.itcen.censquare.domain.workplace.dto.WorkplaceDTO;
import com.itcen.censquare.domain.workplace.entity.Workplace;
import com.itcen.censquare.domain.workplace.mapper.WorkplaceMapper;
import com.itcen.censquare.domain.workplace.repository.WorkplaceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkplaceService {


  private final WorkplaceRepository workplaceRepository;
  private final WorkplaceMapper workplaceMapper;

  /**
   * 복합 필터링 조건을 고려하여 근무지 리스트 반환 - 조건이 null이 아닌 항목만 기준으로 삼아 동적 필터링 수행
   */
  public List<WorkplaceDTO> getFilteredWorkplaces(List<String> corp, List<String> region,
      List<String> division) {
    List<Workplace> workplaces = workplaceRepository.findAll().stream()
        .filter(w -> corp == null || corp.isEmpty() || corp.contains(w.getCorporationName()))
        .filter(w -> region == null || region.isEmpty() || region.contains(w.getField()))
        .filter(
            w -> division == null || division.isEmpty() || division.contains(w.getDivisionName()))
        .collect(Collectors.toList());

    return workplaceMapper.toDtoList(workplaces);
  }
}
