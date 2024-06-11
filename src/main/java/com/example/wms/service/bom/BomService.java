package com.example.wms.service.bom;

import com.example.wms.dto.bom.BomDTO;
import com.example.wms.entity.bom.Bom;

public interface BomService {

    Long register(BomDTO bomDTO);
    // bom 테이블에 등록하는 메서드

    BomDTO readOne(Long bNo);
    // bom id에 해당하는 하나만 반환하는 메서드

    Long modify(BomDTO bomDTO);
    // BomDTO를 받아 수정하는 메서드

    void remove(Long bNo);
    // bom id에 해당하는 하나를 삭제하는 메서드

    default Bom dtoToEntity(BomDTO bomDTO){
        return Bom.builder()
                .bNo(bomDTO.getBNo())
                .pNo(bomDTO.getPNo())
                .bQty(bomDTO.getBQty())
                .gNo(bomDTO.getGNo())
                .bNote(bomDTO.getBNote())
                .build();
    }

    default BomDTO entityToDTO(Bom bom){
        return BomDTO.builder()
                .bNo(bom.getBNo())
                .pNo(bom.getPNo())
                .bQty(bom.getBQty())
                .gNo(bom.getGNo())
                .bNote(bom.getBNote())
                .build();
    }

}
