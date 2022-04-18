package com.study.board.repository;

import com.study.board.vo.BoardItemVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

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
    void selectAll() {
        List<BoardItemVO> list = postRepository.selectAll();
        assertEquals("sample",list.get(0).getTitle());
    }
}