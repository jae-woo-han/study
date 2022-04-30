package com.study.board.repository;

import com.study.board.vo.CategoryInfoVO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Category
 * Repository Test
 *
 * 해당 메소드 작동 테스트
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;
    @Test
    void selectCategoryAll() {
        List<CategoryInfoVO> list = categoryRepository.selectCategoryAll();
        assertEquals("공지", list.get(0).getCategoryName(), list.toString());
    }
}