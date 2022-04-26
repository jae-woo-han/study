package com.study.board.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;
/**
 * File
 * Repository Test
 *
 * 해당 메소드 작동 테스트
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class FileRepositoryTest {

    @Test
    void selectFilesByPostId() {
    }

    @Test
    void insertFileInfo() {
    }
}