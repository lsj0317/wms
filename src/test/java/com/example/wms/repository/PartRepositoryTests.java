package com.example.wms.repository;

import com.example.wms.entity.part.Part;
import com.example.wms.entity.part.PartHistory;
import com.example.wms.repository.part.PartHistoryRepository;
import com.example.wms.repository.part.PartRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PartRepositoryTests {

    @Autowired
    PartRepository partRepository;

    @Autowired
    PartHistoryRepository partHistoryRepository;

    // 한개의 자재 입력 메서드(create)
    @Test
    public void testCreatePart() {
        Part part = Part.builder()
                .pCode("wms-1211")
                .pName("삼각김밥")
                .pQty(22)
                .pSpec("sdsd 사이즈")
                .pType("부자재")
                .build();
        partRepository.save(part);
    }

    // 한개의 자재 수정 메서드(update)
    @Test
    public void testUpdatePart() {
        Long partNum = 1L; // 변경할 자재의 번호
        Optional<Part> partOptional = partRepository.findById(partNum);

        // 자재가 존재하는지 확인
        partOptional.ifPresent(part -> {
            // 새로운 코드로 자재 정보 업데이트
            part.setPCode("wms-2345");

            // 업데이트된 자재 정보 저장
            partRepository.save(part);
        });
    }

    // 한개의 자재 삭제 메서드(delete)
    @Test
    public void testDeletePart() {
        Long partNum = 1L; // 삭제할 자재의 번호

        // 삭제할 자재를 찾아서 삭제
        partRepository.deleteById(partNum);
    }

    // 한개의 자재 수량을 증가시키는 메서드 (증가)
    @Test
    public void testIncreasePart() {
        Long partNum = 3L; // 자재 번호
        int addQty = 10; // 증가할 수량
        Optional<Part> parts = partRepository.findById(partNum);
        // 자재가 존재하는지 확인
        if (parts.isPresent()) {
            Part part = parts.get();
            int initialQty = part.getPQty(); // 비교를 위해 초기 수량 저장
            // 새로운 코드로 자재 정보 업데이트
            part.setPQty(initialQty + addQty);
            // 업데이트된 자재 정보 저장
            partRepository.save(part);

            // 수량이 증가되었는지 확인하기 위해 데이터베이스에서 자재 다시 검색
            Optional<Part> updatedPart = partRepository.findById(partNum);
            updatedPart.ifPresent(p -> {
                assertEquals(initialQty + addQty, p.getPQty(), "수량이 " + addQty + "만큼 증가해야 합니다");

                // 수량 증가 기록 생성
                PartHistory stockHistory = PartHistory.builder()
                        .pNo(partNum)
                        .phQty(addQty)
                        .pType(PartHistory.StockType.IN)
                        .build();
                partHistoryRepository.save(stockHistory);
            });
        } else {
            // 자재가 존재하지 않는 경우 처리
            System.out.println("해당 번호의 자재가 존재하지 않습니다: " + partNum);
        }
    }

    // 한개의 자재 수량을 감소시키는 메서드 (감소)
    @Test
    public void testDecreasePart() {
        Long partNum = 2L; // 자재 번호
        int minusQty = 10; // 감소할 수량
        Optional<Part> parts = partRepository.findById(partNum);
        // 자재가 존재하는지 확인
        if (parts.isPresent()) {
            Part part = parts.get();
            int initialQty = part.getPQty(); // 비교를 위해 초기 수량 저장
            // 새로운 코드로 자재 정보 업데이트
            if (initialQty >= minusQty) { // 자재의 수량이 감소할 수량과 같거나 크면
                part.setPQty(initialQty - minusQty);
                // 업데이트된 자재 정보 저장
                partRepository.save(part);

                // 수량이 감소되었는지 확인하기 위해 데이터베이스에서 자재 다시 검색
                Optional<Part> updatedPart = partRepository.findById(partNum);
                updatedPart.ifPresent(p -> {
                    assertEquals(initialQty - minusQty, p.getPQty(), "현재 수량은 " + p.getPQty() + "입니다.");

                    // 수량 감소 기록 생성
                    PartHistory stockHistory = PartHistory.builder()
                            .pNo(partNum)
                            .phQty(minusQty)
                            .pType(PartHistory.StockType.OUT)
                            .build();
                    partHistoryRepository.save(stockHistory);
                });
            } else { // 만약 감소할 수량보다 작으면
                System.out.println("수량이 부족합니다: " + partNum + " (현재 수량: " + initialQty + ")");
                return;
            }
        } else {
            // 해당 자재가 존재하지 않는 경우 처리
            System.out.println("해당 번호의 자재가 존재하지 않습니다: " + partNum);
        }
    }

    // 리스트 불러오기(list - paging)
    @Test
    public void testGetListPaging() {
        // 1페이지 10개
        Pageable pageable = PageRequest.of(1, 10);
        Page<Part> result = partRepository.findAll(pageable);
        System.out.println(result);
        System.out.println("===============================");
        System.out.println("페이지 수 : " + result.getTotalPages());
        System.out.println("게시물 수 : " + result.getTotalElements());
        System.out.println("현재 페이지의 번호 : " + result.getNumber());
        System.out.println("페이지당 게시물 수 : " + result.getSize());
        System.out.println("다음 페이지 존재 여부 : " + result.hasNext());
        System.out.println("시작 페이지 존재 여부 : " + result.isFirst());
    }

    // 한개의 자재 조회(get)
    @Test
    public void testGetPart() {
        Long partNum = 2L;
        Optional<Part> part = partRepository.findById(partNum);
        System.out.println(part);
    }

    // 정렬 테스트
    @Test
    public void testSort() {
        Sort sort = Sort.by("pNo").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Part> result = partRepository.findAll(pageable);
        result.get().forEach(part -> {
            System.out.println(part);
        });
    }

}