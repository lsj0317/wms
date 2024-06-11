package com.example.wms.repository;

import com.example.wms.entity.goods.Goods;
import com.example.wms.repository.goods.GoodsRepository;
import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void testInsert() {
        LongStream.rangeClosed(1, 20).forEach(i -> {

            Goods goods = Goods.builder()
                    .bNo(12345 + i)
                    .gCode("1234567890" + i)
                    .gName("testItem" + i)
                    .gSpec("testSpec" + ( i % 10 ))
                    .build();

            Goods result = goodsRepository.save(goods);

            System.out.println("GNO: " + result.getGNo());
        });
    }

    @Test
    public void testSelect() {

        Long gNo = 120L;

        Optional<Goods> result = goodsRepository.findById(gNo);

        Goods goods = result.orElseThrow();

        System.out.println(goods);
    }

    @Test
    public void testUpdate() {

        Long gNo = 120L;

        Optional<Goods> result = goodsRepository.findById(gNo);

        Goods goods = result.orElseThrow();

        goods.change("12375", "testItem12", "testSpec1234" );

        goodsRepository.save(goods);

    }

    @Test
    public void testDelete() {

        Long gNo = 101L;

        goodsRepository.deleteById(gNo);
    }

    @Test
    public void testPaging() {

        // 1page order by gno desc
        Pageable pageable = PageRequest.of(0, 10 , Sort.by("gno").descending());

        Page<Goods> result = goodsRepository.findAll(pageable);

        System.out.println("code : " + result.getTotalElements());
        System.out.println("name : " + result.getTotalPages());
        System.out.println("page number : " + result.getNumber());
        System.out.println("page size :" + result.getSize());

        List<Goods> todoList = result.getContent();

        todoList.forEach(goods -> System.out.println(goods));
    }
}
