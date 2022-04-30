package com.study.board.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Transactional
@RestController
@RequestMapping("api")
public class HomeRestController {
    @GetMapping("")
    public String hello(){
        return "hello";
    }

}
