package com.example.wms.controller.bom;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bom")
@Log4j2
public class BomController {

    @GetMapping("/ex")
    public void test(){};

}
