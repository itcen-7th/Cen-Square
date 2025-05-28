package com.itcen.censquare.domain.workplace.controller;

import com.itcen.censquare.domain.workplace.dto.WorkplaceDTO;
import com.itcen.censquare.domain.workplace.service.WorkplaceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WorkplaceController {

  private final WorkplaceService workplaceService;

  /**
   * 근무지 지도 페이지 요청 처리 컨트롤러. 필터 조건이 없으면 전체 근무지를, 조건이 있으면 해당 조건에 맞는 근무지를 반환함.
   */
  @GetMapping("/workplace")
  public String getWorkplacePage(
      @RequestParam(required = false) List<String> corp,
      @RequestParam(required = false) List<String> region,
      @RequestParam(required = false) List<String> division,
      Model model
  ) {
    // 필터 조건을 모두 고려하여 근무지 목록 조회
    List<WorkplaceDTO> workplaces = workplaceService.getFilteredWorkplaces(corp, region, division);
    model.addAttribute("workplaces", workplaces);
    return "workplace"; // templates/workplace.html 렌더링
  }

}
