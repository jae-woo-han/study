package com.study.board.controller;

import com.study.board.exception.ValidateException;
import com.study.board.repository.CategoryRepository;
import com.study.board.repository.PostRepository;
import com.study.board.service.PostService;
import com.study.board.vo.PostCreateForm;
import com.study.board.vo.PostViewVO;
import org.apache.commons.lang3.StringUtils;
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

    public PostController(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
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

    @GetMapping("/passConfirm/update/{postId}")
    public String movePostUpdateConfirmPage(Model model,@PathVariable("postId")int postId){
        model.addAttribute("postId", postId);
        model.addAttribute("method","update");

        return "confirmPost";
    }
    @GetMapping("/passConfirm/delete/{postId}")
    public String movePostDeleteConfirmPage(Model model,@PathVariable("postId")int postId){
        model.addAttribute("postId", postId);
        model.addAttribute("method","delete");

        return "confirmPost";
    }
    @PostMapping("/post/{postId}/update")
    public String movePostUpdatePage(Model model, @PathVariable("postId") int postId,String password){
        String selectPassword = postRepository.selectPostPasswordOne(postId);

        PostViewVO postView;
        if(StringUtils.isNoneEmpty(password)){
            logger.debug("********password Confirm********");
            logger.debug(password+" == "+selectPassword);
            if(StringUtils.equals(password,selectPassword)){

                postView = postRepository.selectPostOne(postId);
                model.addAttribute("postView",postView);
            }else {
                throw new ValidateException("비밀번호 불일치");
            }
        }

        return "updatePost";
    }
    @PostMapping("/post/{postId}/delete")
    public RedirectView movePostDeletePage(Model model, @PathVariable("postId") int postId,String password){
        String selectPassword = postRepository.selectPostPasswordOne(postId);

        PostViewVO postView;
        if(StringUtils.isNoneEmpty(password)){
            logger.debug("********password Confirm********");
            logger.debug(password+" == "+selectPassword);
            if(StringUtils.equals(password,selectPassword)){
                postRepository.deletePostOne(postId);
                model.addAttribute("alertMessage","삭제가 완료되었습니다.");
            }else {
                throw new ValidateException("비밀번호 불일치");
            }
        }

        return new RedirectView("/board");
    }
}
