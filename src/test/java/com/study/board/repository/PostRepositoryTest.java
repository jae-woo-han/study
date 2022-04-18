package com.study.board.repository;

import com.study.board.vo.BoardItemVO;
import com.study.board.vo.PostSearchForm;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
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
        PostSearchForm form = PostSearchForm
                .builder()
                .build();
        List<BoardItemVO> list = postRepository.selectBoardViewList(form);
        assertEquals("제목",list.get(0).getTitle());
    }
}