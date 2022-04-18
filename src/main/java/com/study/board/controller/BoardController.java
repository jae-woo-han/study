package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class BoardController {

    @RequestMapping("/home")
    public String initPage(){
        return "index";
    }
    @RequestMapping("/board")
    public String moveBoardPage(){
        return "board";
    }
}
