package com.study.board.vo;

import lombok.Data;

@Data
public class PostCreateForm {
    private int postId;
    private int categoryId;
    private String title;
    private String postContent;
    private String writer;
    private String password;
    private String passwordConfirm;
}
