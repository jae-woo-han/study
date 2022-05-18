package com.study.board.controller.api;

import com.study.board.repository.PostRepository;
import com.study.board.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Transactional
@RestController
@RequestMapping("api")
public class PostApiController {

    private final PostRepository postRepository;

    public PostApiController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/page/{pageNum}")
    public ResponseEntity<ResponseVO<List<BoardItemVO>> > getPostListWithPage(@PathVariable("pageNum") int pageNum, PostSearchForm postSearchForm) {
        postSearchForm.setStart((pageNum > 0) ? (pageNum - 1) * 10 : 0);
        ResponseVO<List<BoardItemVO>> responseData = new ResponseVO<>("성공",postRepository.selectBoardViewList(postSearchForm));
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseVO<PageApiVO>> getPaging(@RequestParam(value = "postSearchForm", required = false) PostSearchForm postSearchForm) {
        PageApiVO pageApiVO = new PageApiVO(postRepository.selectPostCount(postSearchForm));
        ResponseVO<PageApiVO> responseData = new ResponseVO<>("성공",pageApiVO);
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ResponseVO<PostViewVO>> getPostViewByPostId(@PathVariable("postId") int postId) {
        ResponseVO<PostViewVO> responseData = new ResponseVO<>("성공",postRepository.selectPostOne(postId));
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseVO<PostViewVO>> writePostViewByPostId(@RequestBody @Validated PostCreateForm postCreateForm) {
        log.debug("post create form : {}", postCreateForm.toString());
        postRepository.insertPost(postCreateForm);
        ResponseVO<PostViewVO> responseData = new ResponseVO<>("성공",postRepository.selectPostOne(postCreateForm.getPostId()));
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

    @PutMapping("/post")
    public ResponseEntity<ResponseVO<Integer>> updatePostViewByPostId(@RequestBody PostUpdateForm postUpdateForm) {
        log.debug("post update form : {}", postUpdateForm.toString());
        postRepository.updatePostOne(postUpdateForm);
        ResponseVO<Integer> responseData = new ResponseVO<>("성공",postUpdateForm.getPostId());
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ResponseVO<Boolean>> deletePostByPostId(@PathVariable("postId") int postId) {
        postRepository.deletePostOne(postId);
        ResponseVO<Boolean> responseData = new ResponseVO<>("성공",true);
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

    @PostMapping("/check/password/{postId}")
    public ResponseEntity<ResponseVO<Boolean>> checkPassword(
            @PathVariable("postId") int postId
            , @RequestBody Map<String,String> passwordData) {
        log.debug("비밀번호 확인 : {}",passwordData);
        String passwordConfirm = passwordData.get("passwordConfirm");
        String password = postRepository.selectPostPasswordOne(postId);

        ResponseVO<Boolean> responseData;
        if (StringUtils.equals(password, passwordConfirm)) {
            responseData = new ResponseVO<>("성공",true);
            return new ResponseEntity<>(responseData, null, HttpStatus.OK);
        }
        responseData = new ResponseVO<>("비밀번호 불일치",false);
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/view")
    public ResponseEntity<ResponseVO<PostViewVO>> getPostViewAndUpdateViewCountByPostId(
            @PathVariable("postId") int postId
    ){
        PostViewVO postViewVO = postRepository.selectPostOne(postId);
        postRepository.updateViewCount(postId,postViewVO.getViewCount()+1);
        ResponseVO<PostViewVO> responseData = new ResponseVO<>("성공",postRepository.selectPostOne(postId));
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

}
