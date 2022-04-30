package com.study.board.vo;

import lombok.Data;

@Data
public class PostUpdateForm {
    private int postId;
    private String writer;
    private String password;
    private String title;
    private String postContent;
}
