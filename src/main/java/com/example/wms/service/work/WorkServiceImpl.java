package com.example.wms.service.work;

import com.example.wms.dto.work.WorkDTO;
import com.example.wms.entity.work.Work;
import com.example.wms.repository.work.WorkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;

    @Override // work 테이블에 등록하는 메서드
    public Long register(WorkDTO workDTO) {
        Work work = dtoToEntity(workDTO);
        return workRepository.save(work).getWNo();
    }

    @Override // 생산 id에 해당하는 하나만 반환하는 메서드
    public WorkDTO readOne(Long wNo) {
        Optional<Work> result = workRepository.findById(wNo);
        WorkDTO workDTO = entityToDTO(result.orElseThrow());
        return workDTO;
    }

    @Override // 생산 DTO를 받아 수정하는 메서드
    public Long modify(WorkDTO workDTO) {
        Optional<Work> result = workRepository.findById(workDTO.getWNo());
        Work work = result.orElseThrow();
        work.changeWork(workDTO.getGNo(), workDTO.getWQty());
        return workRepository.save(work).getWNo();
    }

    @Override // 생산 id에 해당하는 하나를 삭제하는 메서드
    public void remove(Long wNo) {
        workRepository.deleteById(wNo);
    }
}
