package com.study.board.service;

import com.study.board.vo.PostCreateForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Post
 * Service Test
 *
 * 해당 메소드 작동 테스트
 */
@SpringBootTest
class PostServiceTest {
    @Autowired
    PostService postService;

    @Test
    void getLastInsertKeyInPostInfo() {
        PostCreateForm form= new PostCreateForm();
        form.setCategoryId(1);
        form.setTitle("등록 테스트");
        form.setPostContent("asdfdf");
        form.setWriter("등록자");
        form.setPassword("1234ar");

        assertEquals(61,postService.getLastInsertKeyInPostInfo(form));
    }
}