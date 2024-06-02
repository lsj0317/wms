package com.example.wms.controller.part;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/part")
@Log4j2
public class PartController {

    // 테스트 페이지
    @GetMapping("/test")
    public void test() {
        log.info("test..............");
    }


}
