package com.study.board.controller;

import com.study.board.repository.PostRepository;
import com.study.board.vo.BoardItemVO;
import com.study.board.vo.PageVO;
import com.study.board.vo.PostSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
@Transactional
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
        logger.debug("searchForm :"+searchForm);
        int pageNum = searchForm.getStart();
        searchForm.setStart((pageNum>0)?(pageNum-1)*10:0);
        PageVO<List<BoardItemVO>> pageVO = getPageVO(searchForm);
        if(pageNum>0) pageVO.setCurrentPage(pageNum);
        model.addAttribute("postList",pageVO);
        return "board";
    }
    @RequestMapping("/board/{pageNum}")
    public String selectPage(Model model, PostSearchForm searchForm, @PathVariable("pageNum") int pageNum){
        searchForm.setStart(
                (pageNum>0)?(pageNum-1)*10:0
        );
        logger.debug("searchForm :"+searchForm);
        PageVO<List<BoardItemVO>> pageVO = getPageVO(searchForm);
        model.addAttribute("postList",pageVO);

        return "board";
    }

    private PageVO<List<BoardItemVO>> getPageVO(PostSearchForm searchForm) {
        List<BoardItemVO> postList = postRepository.selectBoardViewList(searchForm);
        int totalCount = postRepository.selectPostCount(searchForm);

        PageVO<List<BoardItemVO>> pageVO = new PageVO<>(totalCount);
        pageVO.setData(postList);
        return pageVO;
    }

    @RequestMapping("/post/{postId}")
    public String movePostViewPage(Model model,@PathVariable("postId") int postId){

        return "postView";
    }
}
