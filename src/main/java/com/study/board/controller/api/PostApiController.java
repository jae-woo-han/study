package com.study.board.controller.api;

import com.study.board.repository.PostRepository;
import com.study.board.vo.BoardItemVO;
import com.study.board.vo.PageApiVO;
import com.study.board.vo.PostSearchForm;
import com.study.board.vo.PostViewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<BoardItemVO>> getPostListWithPage(@PathVariable("pageNum")int pageNum,PostSearchForm postSearchForm){
        postSearchForm.setStart((pageNum>0)?(pageNum-1)*10:0);
        return new ResponseEntity<>(postRepository.selectBoardViewList(postSearchForm),null, HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<PageApiVO> getPaging(PostSearchForm postSearchForm){
        PageApiVO pageApiVO = new PageApiVO(postRepository.selectPostCount(postSearchForm));
        return new ResponseEntity<>(pageApiVO, null, HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostViewVO> getPostViewByPostId(@PathVariable("postId") int postId){
        PostViewVO postViewVO = postRepository.selectPostOne(postId);
        return new ResponseEntity<>(postViewVO, null, HttpStatus.OK);
    }

}
