package com.example.wms.service;

import com.example.wms.entity.part.Part;
import com.example.wms.entity.part.PartHistory;
import com.example.wms.repository.part.PartHistoryRepository;
import com.example.wms.repository.part.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartHistoryRepository partHistoryRepository;

    // 한개의 자재 입력 메서드(create)
    public void cratePart() {
        Part part = Part.builder()
                .pCode("wms-1234")
                .pName("사각너트")
                .pQty(10)
                .pSpec("12mm 12*12 사이즈")
                .pType("부자재")
                .build();
        partRepository.save(part);
    }
    // 한개의 자재 수정 메서드(update)
    public void updatePart() {
        Long partNum = 1L; // 변경할 자재의 번호
        Optional<Part> partOptional = partRepository.findById(partNum);

        // 자재가 존재하는지 확인
        if (partOptional.isPresent()) {
            Part part = partOptional.get();

            // 새로운 코드로 자재 정보 업데이트
            part.setPCode("wms-2345");

            // 업데이트된 자재 정보 저장
            partRepository.save(part);
        } else {
            // 해당 자재가 존재하지 않는 경우 처리
            // 예외를 던지는 방법
            //throw new IllegalArgumentException("해당 번호의 자재가 존재하지 않습니다: " + partNum);
            // 혹은 로그를 남기는 방법
            System.out.println("해당 번호의 자재가 존재하지 않습니다: " + partNum);
            // 또는 Logger 클래스를 사용하여 로깅
        }
    }
    // 한개의 자재 삭제 메서드(delete)
    public void deletePart() {
        Long partNum = 1L; // 삭제할 자재의 번호
        Optional<Part> partOptional = partRepository.findById(partNum);
        partRepository.deleteById(partNum);
    }
    // 한개의 자재 수량 더하는 메서드(Increase)
    public void increasePart() {

    }
    // 한개의 자재 수량 빼는 메서드(Decrease)
    public void decreasePart() {

    }
    // 리스트 불러오기(list - paging)
    public void getLisePaging() {

    }
    // 한개의 자재 조회(get)
    public void getPart() {

    }
}
