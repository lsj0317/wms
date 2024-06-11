package com.example.wms.service.work;

import com.example.wms.dto.work.WorkDTO;
import com.example.wms.entity.work.Work;

public interface WorkService {

    Long register(WorkDTO workDTO);
    // work 테이블에 등록하는 메서드

    WorkDTO readOne(Long wNo);
    // 생산 id에 해당하는 하나만 반환하는 메서드

    Long modify(WorkDTO workDTO);
    // 생산 DTO를 받아 수정하는 메서드

    void remove(Long wNo);
    // 생산 id에 해당하는 하나를 삭제하는 메서드

    default Work dtoToEntity(WorkDTO workDTO){
        return Work.builder()
                .wNo(workDTO.getWNo())
                .gNo(workDTO.getGNo())
                .wQty(workDTO.getWQty())
                .build();
    }

    default WorkDTO entityToDTO(Work work){
        return WorkDTO.builder()
                .wNo(work.getWNo())
                .gNo(work.getGNo())
                .wQty(work.getWQty())
                .wRegDate(work.getRegDate())
                .wModDate(work.getModDate())
                .build();
    }

}
