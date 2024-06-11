package com.example.wms.controller.part;

import com.example.wms.dto.part.PartDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/part")
@Log4j2
public class PartController {

    // 테스트 페이지
    @GetMapping("/test")
    public void test() {
        log.info("test..............");
    }


    // 리스트 페이지
    @GetMapping({"/", "/list"})
    public String list() {
        log.info("list...");
        return "/part/list";
    }

}
