package com.example.wms.repository;

import com.example.wms.entity.bom.Bom;
import com.example.wms.repository.bom.BomRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class BomRepositoryTests {

    @Autowired
    BomRepository bomRepository;

    // C
    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 3).forEach(i -> {
            LongStream.rangeClosed(1, 3).forEach(j -> {
                Bom bom = Bom.builder()
                        .pNo(j)
                        .bQty((int) (i * 5 + j * 5))
                        .gNo(i)
                        .build();

                log.info(bomRepository.save(bom).getBNo());
            });
        });
    }

    // R
    @Test
    public void searchTest(){
        // bom id에 해당하는 정보를 반환(1개)
        Long bNo = 6L;
        Optional<Bom> result = bomRepository.findById(bNo);
        result.ifPresent(bom -> log.info("bom : " + bom));
    }

    @Test
    public void listTest(){
        // bom 테이블 전체를 반환(list)
        List<Bom> result = bomRepository.findAll();
        result.forEach(bom -> log.info("result : " + bom));
    }

    @Test
    public void searchByGnoTest(){
        // 상품 id에 해당하는 부품(bom)정보를 반환(list)
        // 상품의 부품들을 조회할 때에 사용
        Long gNo = 2L;
        List<Bom> result = bomRepository.findBygNo(gNo);
        result.forEach(bom -> log.info(gNo + "번 상품의 부품 : " + bom));
    }

    @Test
    public void searchByPnoTest(){
        // 부품 id에 해당하는 상품 id를 반환
        // 부품이 어떤상품들에 쓰였는지 조회할 때에 사용
        Long pNo = 2L;
        List<Long> result = bomRepository.searchGnoByPno(pNo);
        result.forEach(r -> log.info(pNo + "번 부품을 사용하는 상품들 : " + r));
    }

    // U
    @Test
    public void updateTest(){
        Optional<Bom> result = bomRepository.findById(2L);
        result.ifPresent(bom -> {
            bom.changeBom(3L, 50, 3L, "비고");
            log.info(bomRepository.save(bom));
        });
    }

    // D
    @Test
    public void deleteTest(){
        Long bNo = 8L;
        bomRepository.deleteById(bNo);
    }




}