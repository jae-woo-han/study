package com.study.board.controller;

import com.study.board.repository.CategoryRepository;
import com.study.board.repository.PostRepository;
import com.study.board.service.PostService;
import com.study.board.vo.PostCreateForm;
import com.study.board.vo.PostViewVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Transactional
@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final PostService postService;

    public PostController(PostRepository postRepository, CategoryRepository categoryRepository, PostService postService) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.postService = postService;
    }
    @GetMapping("/post/{postId}")
    public String moveViewPostPage(Model model, @PathVariable("postId") int postId){
        PostViewVO postView = postRepository.selectPostOne(postId);
        model.addAttribute("postView",postView);
        return "viewPost";
    }
    @GetMapping("/write")
    public String movePostWritePage(Model model){
        model.addAttribute("categoryList",categoryRepository.selectCategoryAll());
        return "writePost";
    }
    @PostMapping("/post")
    public RedirectView writePost(Model model,PostCreateForm postForm){
        postRepository.insertPost(postForm);
        int insertedPostId = postForm.getPostId();
        model.addAttribute("postId",insertedPostId);
        String redirectPath = "/post/"+Integer.toString(insertedPostId);
        return new RedirectView(redirectPath);
    }
}
