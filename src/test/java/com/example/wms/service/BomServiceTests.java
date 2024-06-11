package com.example.wms.service;

import com.example.wms.dto.bom.BomDTO;
import com.example.wms.service.bom.BomService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BomServiceTests {

    @Autowired
    private BomService bomService;

    // C
    @Test
    public void registerTest(){
        BomDTO bomDTO = BomDTO.builder()
                .pNo(3L)
                .bQty(10)
                .gNo(3L)
                .bNote("registerTest")
                .build();
        Long bNo = bomService.register(bomDTO);
        log.info("register result : " + bNo);
    }

    // R
    @Test
    public void readOneTest(){
        Long bNo = 1L;
        log.info("readOne result : " + bomService.readOne(bNo));
    }

    // U
    @Test
    public void modifyTest(){
        BomDTO bomDTO = BomDTO.builder()
                .bNo(4L)
                .pNo(5L)
                .bQty(50)
                .gNo(5L)
                .bNote("modifyTest")
                .build();
        log.info(bomService.modify(bomDTO));
    }

    // D
    @Test
    public void removeTest(){
        Long bNo = 3L;
        bomService.remove(bNo);
    }

}