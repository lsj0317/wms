package com.example.wms.repository;

import com.example.wms.entity.work.Work;
import com.example.wms.repository.work.WorkRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class WorkRepositoryTests {

    @Autowired
    WorkRepository workRepository;

    // C
    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 3).forEach(i -> {
                Work work = Work.builder()
                        .gNo(i)
                        .wQty(30)
                        .build();

                log.info(workRepository.save(work).getWNo());
        });
    }

    // R
    @Test
    public void searchTest(){
        // 생산 id에 해당하는 정보를 반환(1개)
        Long wNo = 2L;
        Optional<Work> result = workRepository.findById(wNo);
        result.ifPresent(work -> {
            log.info("work : " + work);
            log.info("workRegDate : " + work.getRegDate());
            log.info("workModDate : " + work.getModDate());
        });
    }

    @Test
    public void listTest(){
        // work 테이블 전체를 반환(list)
        List<Work> result = workRepository.findAll();
        result.forEach(work -> {
            log.info("-------------------------------------------------------");
            log.info("work : " + work);
            log.info("workRegDate : " + work.getRegDate());
            log.info("workModDate : " + work.getModDate());
        });
    }

    @Test
    public void searchByGno(){
        Long gNo = 1L;
        List<Work> result = workRepository.findBygNo(gNo);
        result.forEach(work -> log.info(gNo + "번 상품의 생산내역 : " + work));
    }

    // U
    @Test
    public void updateTest(){
        Optional<Work> result = workRepository.findById(2L);
        result.ifPresent(work -> {
            work.changeWork(1L, 15);
            log.info(workRepository.save(work));
        });
    }

    // D
    @Test
    public void deleteTest(){
        Long wNo = 5L;
        workRepository.deleteById(wNo);
    }




}