package com.study.board.controller;

import com.study.board.repository.PostRepository;
import com.study.board.vo.BoardItemVO;
import com.study.board.vo.PageVO;
import com.study.board.vo.PostSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    //처음은 service 단 없이 진행
    private final PostRepository postRepository;

    public BoardController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping("/home")
    public String initPage(){
        return "index";
    }
    @RequestMapping("/board")
    public String moveBoardPage(Model model,PostSearchForm searchForm){
        List<BoardItemVO> postList = postRepository.selectBoardViewList(searchForm);

        PageVO<List<BoardItemVO>> pageVO = new PageVO<>();
        model.addAttribute("postList",postList);
        return "board";
    }

    @RequestMapping("/post/{postId}")
    public String movePostViewPage(Model model,@PathVariable("postId") int postId){

        return "postView";
    }
}
