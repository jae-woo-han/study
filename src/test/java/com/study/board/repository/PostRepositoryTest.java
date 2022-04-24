package com.study.board.repository;

import com.study.board.vo.*;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Post
 * Repository Test
 *
 * 해당 메소드 작동 테스트
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    void selectBoardViewList() {
        PostSearchForm form = new PostSearchForm();
        List<BoardItemVO> list = postRepository.selectBoardViewList(form);
        assertEquals(47,list.size());
    }

    @Test
    void selectPostOne(){
        PostViewVO post = postRepository.selectPostOne(1);
        assertEquals("sample",post.getTitle());
    }
    @Test
    void selectPostCount(){
        PostSearchForm form = new PostSearchForm();
        int count = postRepository.selectPostCount(form);
        assertEquals(47,count);
    }
    @Test
    void insertPost(){
        PostCreateForm form= new PostCreateForm();
        form.setCategoryId(1);
        form.setTitle("등록 테스트");
        form.setPostContent("asdfdf");
        form.setWriter("등록자");
        form.setPassword("1234ar");
        postRepository.insertPost(form);
        int result =form.getPostId();

        assertEquals(72,result);
    }
    @Test
    void updatePostOne(){
        PostUpdateForm form = new PostUpdateForm();
        form.setPostId(2);
        form.setWriter("수정자");
        form.setTitle("수정테스트");
        form.setPostContent("csccccccc");

        postRepository.updatePostOne(form);
        PostViewVO view = postRepository.selectPostOne(form.getPostId());

        assertEquals(form.getWriter(),view.getWriter());
    }
}