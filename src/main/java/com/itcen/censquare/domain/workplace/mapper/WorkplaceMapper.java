package com.itcen.censquare.domain.workplace.mapper;

import com.itcen.censquare.domain.workplace.dto.WorkplaceDTO;
import com.itcen.censquare.domain.workplace.entity.Workplace;
import java.util.List;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface WorkplaceMapper {

  WorkplaceDTO toDto(Workplace entity);

  Workplace toEntity(WorkplaceDTO dto);

  List<WorkplaceDTO> toDtoList(List<Workplace> entities);
}
