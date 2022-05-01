package com.study.board.controller.api;

import com.study.board.vo.PostViewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Transactional
@RestController
@RequestMapping("api")
public class HomeApiController {
    @GetMapping("")
    public String hello(){
        return "hello";
    }
    @GetMapping("/board")
    public ResponseEntity<PostViewVO> boardVO(){
        PostViewVO postViewVO = new PostViewVO();
        postViewVO.setPostId(1);
        postViewVO.setTitle("restful");
        postViewVO.setPostContent("더미데이터 테스트");
        postViewVO.setCategoryName("RESTFUL");
        postViewVO.setWriter("Fielding");

        return new ResponseEntity<>(postViewVO,null,HttpStatus.OK);
    }

}
