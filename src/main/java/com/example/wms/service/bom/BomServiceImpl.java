package com.example.wms.service.bom;

import com.example.wms.dto.bom.BomDTO;
import com.example.wms.entity.bom.Bom;
import com.example.wms.repository.bom.BomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BomServiceImpl implements BomService {

    private final BomRepository bomRepository;

    @Override // bom 테이블에 등록하는 메서드
    public Long register(BomDTO bomDTO) {
        Bom bom = dtoToEntity(bomDTO);
        return bomRepository.save(bom).getBNo();
    }

    @Override // bom id에 해당하는 하나만 반환하는 메서드
    public BomDTO readOne(Long bNo) {
        Optional<Bom> result = bomRepository.findById(bNo);
        BomDTO bomDTO = entityToDTO(result.orElseThrow());
        return bomDTO;
    }

    @Override // BomDTO를 받아 수정하는 메서드
    public Long modify(BomDTO bomDTO) {
        Optional<Bom> result = bomRepository.findById(bomDTO.getBNo());
        Bom bom = result.orElseThrow();
        bom.changeBom(bomDTO.getPNo(), bomDTO.getBQty(), bomDTO.getGNo(), bomDTO.getBNote());
        return bomRepository.save(bom).getBNo();
    }

    @Override // bom id에 해당하는 하나를 삭제하는 메서드
    public void remove(Long bNo) {
        bomRepository.deleteById(bNo);
    }

}
