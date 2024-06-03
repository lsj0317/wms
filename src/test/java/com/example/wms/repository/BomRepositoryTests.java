package com.example.wms.repository;

import com.example.wms.entity.bom.Bom;
import com.example.wms.entity.part.Part;
import com.example.wms.entity.part.PartStockHistory;
import com.example.wms.entity.part.PartStockHistory.StockType;
import com.example.wms.repository.bom.BomRepository;
import com.example.wms.repository.part.PartRepository;
import com.example.wms.repository.part.PartStockHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
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
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class BomRepositoryTests {

    @Autowired
    BomRepository bomRepository;

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

    @Test
    public void searchTest(){
        Long bNo = 1L;
        Optional<Bom> result = bomRepository.findById(bNo);
        result.ifPresent(bom -> log.info("bom : " + bom));
    }



}