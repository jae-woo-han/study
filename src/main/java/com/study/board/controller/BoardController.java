package com.study.board.controller;

import com.study.board.vo.PostSearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class BoardController {

    @RequestMapping("/home")
    public String initPage(){
        return "index";
    }
    @RequestMapping("/board")
    public String moveBoardPage(PostSearchForm searchForm){


        return "board";
    }

    @RequestMapping("/post/{postId}")
    public String movePostViewPage(@PathVariable("postId") int postId){

        return "postView";
    }
}
