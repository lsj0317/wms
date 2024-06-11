package com.example.wms.service;

import com.example.wms.dto.goods.GoodsDTO;
import com.example.wms.repository.goods.GoodsRepository;
import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class GoodsServiceTest {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void testRegister() {

        System.out.println(goodsService.getClass().getName());

        GoodsDTO goodsDTO = GoodsDTO.builder()
                .bNo(12348L)
                .gCode("1234567890000")
                .gName("테스트name")
                .gSpec("테스트spec")
                .build();

        Long gno = goodsService.register(goodsDTO);

        System.out.println("gno : " + gno);

    }

    @Test
    public void testModify() {

        GoodsDTO goodsDTO = GoodsDTO.builder()
                .gNo(102L)
                .gCode("2345678900")
                .gName("update...")
                .gSpec("update....spec")
                .build();

        goodsService.modify(goodsDTO);
    }

    @Test
    public void test
}
