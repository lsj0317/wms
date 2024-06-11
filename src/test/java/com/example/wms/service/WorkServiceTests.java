package com.example.wms.service;

import com.example.wms.dto.work.WorkDTO;
import com.example.wms.service.work.WorkService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class WorkServiceTests {

    @Autowired
    private WorkService workService;

    // C
    @Test
    public void registerTest(){
        WorkDTO workDTO = WorkDTO.builder()
                .gNo(3L)
                .wQty(30)
                .build();
        log.info("register result : " + workService.register(workDTO));
    }

    // R
    @Test
    public void readOneTest(){
        Long wNo = 1L;
        log.info(workService.readOne(wNo));
    }

    // U
    @Test
    public void modifyTest(){
        WorkDTO workDTO = WorkDTO.builder()
                .wNo(1L)
                .gNo(2L)
                .wQty(100)
                .build();
        log.info("modify result : " + workService.modify(workDTO));
    }

    // D
    @Test
    public void removeTest(){
        Long wNo = 2L;
        workService.remove(wNo);
    }

}