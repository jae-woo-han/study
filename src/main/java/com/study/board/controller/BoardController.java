package com.study.board.controller;

import com.study.board.repository.CategoryRepository;
import com.study.board.repository.PostRepository;
import com.study.board.vo.BoardItemVO;
import com.study.board.vo.PageVO;
import com.study.board.vo.PostSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Transactional
@Controller
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    //처음은 service 단 없이 진행
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public BoardController(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/home")
    public String initPage(){
        return "index";
    }
    @GetMapping("/board")
    public String moveBoardPage(Model model,PostSearchForm searchForm){
        logger.debug("searchForm :"+searchForm);
        int pageNum = searchForm.getStart();
        searchForm.setStart((pageNum>0)?(pageNum-1)*10:0);
        PageVO<List<BoardItemVO>> pageVO = getPageVO(searchForm);
        if(pageNum>0) pageVO.setCurrentPage(pageNum);
        model.addAttribute("postList",pageVO);
        model.addAttribute("categoryList",categoryRepository.selectCategoryAll());
        return "board";
    }

    private PageVO<List<BoardItemVO>> getPageVO(PostSearchForm searchForm) {
        List<BoardItemVO> postList = postRepository.selectBoardViewList(searchForm);
        int totalCount = postRepository.selectPostCount(searchForm);

        PageVO<List<BoardItemVO>> pageVO = new PageVO<>(totalCount);
        pageVO.setData(postList);
        return pageVO;
    }
}
