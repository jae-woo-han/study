package com.study.board.controller.api;

import com.study.board.repository.PostRepository;
import com.study.board.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<List<BoardItemVO>> getPostListWithPage(@PathVariable("pageNum") int pageNum, PostSearchForm postSearchForm) {
        postSearchForm.setStart((pageNum > 0) ? (pageNum - 1) * 10 : 0);
        return new ResponseEntity<>(postRepository.selectBoardViewList(postSearchForm), null, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<PageApiVO> getPaging(@RequestParam(value = "postSearchForm", required = false) PostSearchForm postSearchForm) {
        PageApiVO pageApiVO = new PageApiVO(postRepository.selectPostCount(postSearchForm));
        return new ResponseEntity<>(pageApiVO, null, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostViewVO> getPostViewByPostId(@PathVariable("postId") int postId) {
        PostViewVO postViewVO = postRepository.selectPostOne(postId);
        return new ResponseEntity<>(postViewVO, null, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<PostViewVO> writePostViewByPostId(@RequestBody PostCreateForm postCreateForm) {
        log.debug("post create form : {}", postCreateForm.toString());
        postRepository.insertPost(postCreateForm);
        return new ResponseEntity<>(postRepository.selectPostOne(postCreateForm.getPostId()), null, HttpStatus.OK);
    }

    @PutMapping("/post")
    public ResponseEntity<PostViewVO> updatePostViewByPostId(@RequestBody PostUpdateForm postUpdateForm) {
        log.debug("post update form : {}", postUpdateForm.toString());
        postRepository.updatePostOne(postUpdateForm);
        return new ResponseEntity<>(postRepository.selectPostOne(postUpdateForm.getPostId()), null, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ResponseVO> deletePostByPostId(@PathVariable("postId") int postId) {
        int result = postRepository.deletePostOne(postId);
        ResponseVO<Boolean> responseData = new ResponseVO<>("삭제 되었습니다.",true);
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

    @PostMapping("/check/password/{postId}")
    public ResponseEntity<ResponseVO> checkPassword(
            @PathVariable("postId") int postId
            , @RequestBody Map<String,String> passwordData) {
        log.debug("비밀번호 확인 : {}",passwordData);
        String passwordConfirm = passwordData.get("passwordConfirm");
        String password = postRepository.selectPostPasswordOne(postId);

        ResponseVO<Boolean> responseData;
        if (StringUtils.equals(password, passwordConfirm)) {
            responseData = new ResponseVO<>("비밀번호 확인",true);
            return new ResponseEntity<>(responseData, null, HttpStatus.OK);
        }
        responseData = new ResponseVO<>("비밀번호 불일치",false);
        return new ResponseEntity<>(responseData, null, HttpStatus.OK);
    }

}
