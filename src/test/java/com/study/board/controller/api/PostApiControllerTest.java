package com.study.board.controller.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest
class PostApiControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    void getPostListWithPage() {
    }

    @Test
    void getPaging() {
    }

    @Test
    void getPostViewByPostId() {
    }

    @Test
    void writePostViewByPostId() {
    }

    @Test
    void updatePostViewByPostId() {
    }

    @Test
    void deletePostByPostId() {
    }

    @Test
    void checkPassword() {
    }
}