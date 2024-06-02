package com.example.wms.repository;

import com.example.wms.entity.part.Part;
import com.example.wms.entity.part.PartStockHistory;
import com.example.wms.entity.part.PartStockHistory.StockType;
import com.example.wms.repository.part.PartRepository;
import com.example.wms.repository.part.PartStockHistoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class PartRepositoryTests {

    @Autowired
    PartRepository partRepository;

    @Autowired
    PartStockHistoryRepository partStockHistoryRepository;

    @Test // 입력 테스트 (여러개)
    public void testInsertData() {
        int addQty = 50; // 입고할 수량

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Part part = Part.builder()
                    .pCode("exPart..." + i)
                    .pName("testPart..." + i)
                    .pSpec("100 * 100 * 50mm 사이즈123")
                    .pType("나사류")
                    .build();

            part.addStock(addQty);

            partRepository.save(part);
            System.out.println("Saved Part: " + part);
        });
    }

    @Test // 입력 테스트 (한개)
    public void testInsertDataOne() {
        int inputQty = 10; // 테스트 입고량

        Part part = Part.builder()
                .pCode("wms-0002")
                .pName("조립 프레임")
                .pQty(inputQty)
                .pSpec("115 * 70 * 22mm 검정색")
                .pType("부품류")
                .build();
        partRepository.save(part);
        System.out.println("Saved Part: " + part);
    }

    @Test // 1개의 상품 조회작업 테스트
    public void testSearchData() {
        // 데이터 베이스에 존재하는 pno
        Long pNo = 101L;

        Optional<Part> result = partRepository.findById(pNo);

        System.out.println("====================================");

        result.ifPresent(part -> System.out.println("Found Part: " + part));
    }

    @Test
    @Transactional
    public void testSearchData2() {
        // 데이터 베이스에 존재하는 pno
        Long pNo = 101L;

        Part part = partRepository.findById(pNo).orElse(null);

        System.out.println("====================================");

        System.out.println("Found Part: " + part);
    }

    @Test // 입고 테스트
    @Transactional
    public void testAddStock() {
        Long pNo = 101L; // 테스트할 자재 번호
        int addQty = 50; // 입고할 수량

        Optional<Part> result = partRepository.findById(pNo);

        result.ifPresent(part -> {
            part.addStock(addQty); // 재고수량 증가
            partRepository.save(part);

            // 입고 기록 생성
            PartStockHistory stockHistory = PartStockHistory.builder()
                    .part(part)
                    .qty(addQty)
                    .dateTime(LocalDateTime.now())
                    .type(StockType.IN)
                    .build();
            partStockHistoryRepository.save(stockHistory);

            System.out.println("Added Stock, Updated Part: " + part);
        });
    }

    @Test // 출고 테스트
    @Transactional
    public void testRemoveStock() {
        Long pNo = 101L; // 테스트할 자재 번호
        int removeQty = 10; // 출고할 수량

        Optional<Part> result = partRepository.findById(pNo);

        result.ifPresent(part -> {
            try {
                part.removeStock(removeQty); // 재고수량 감소
                partRepository.save(part);

                // 출고 기록 생성
                PartStockHistory stockHistory = PartStockHistory.builder()
                        .part(part)
                        .qty(removeQty)
                        .dateTime(LocalDateTime.now())
                        .type(StockType.OUT)
                        .build();
                partStockHistoryRepository.save(stockHistory);

                System.out.println("Removed Stock, Updated Part: " + part);
            } catch (IllegalArgumentException e) {
                System.out.println("예외 발생: " + e.getMessage());
            }
        });
    }

    @Test // 수정 테스트
    public void testUpdate() {

        int inputQty = 55;

        Part part = Part.builder()
                .pNo(1L)
                .pCode("wms-0011")
                .pName("결합 프레임")
                .pQty(inputQty)
                .pSpec("115 * 70 * 22mm 검정색")
                .pType("부품류")
                .build();

        System.out.println(part);
    }

    @Test // 삭제 테스트
    public void testDelete() {
        Long pNo = 101L;
        partRepository.deleteById(pNo);
    }

    @Test // 페이징 처리
    public void testPageDefault() {
        // 1페이지 10개
        Pageable pageable = PageRequest.of(1, 10);
        Page<Part> result = partRepository.findAll(pageable);
        System.out.println(result);

        System.out.println("======================================================");
        System.out.println("Total pages : " + result.getTotalPages()); // 총 몇 페이지
        System.out.println("Total Count : " + result.getTotalElements()); // 전체 갯수
        System.out.println("Page Number : " + result.getNumber()); // 현재 페이지 번호
        System.out.println("Page Size : " + result.getSize()); // 페이지당 데이터 갯수
        System.out.println("has Next Page? : " + result.hasNext()); // 다음 페이지 존재 여부
        System.out.println("first Page? : " + result.isFirst()); // 시작 페이지(0) 여부

        System.out.println("======================================================");
        for(Part part : result.getContent()) {
            System.out.println(part);
        }
    }

    @Test // 정렬 테스트
    public void testSort() {
        Sort sort1 = Sort.by("pNo").descending();
        Pageable pageable = PageRequest.of(0, 10, sort1);
        Page<Part> result = partRepository.findAll(pageable);
        result.get().forEach(part -> {
            System.out.println(part);
        });
    }

}