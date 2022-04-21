package com.study.board.controller;

import com.study.board.repository.PostRepository;
import com.study.board.vo.PostViewVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @RequestMapping("/post/{postId}")
    public String moveViewPostPage(Model model, @PathVariable("postId") int postId){
        PostViewVO postView = postRepository.selectPostOne(postId);
        model.addAttribute("postView",postView);
        return "viewPost";
    }
}
