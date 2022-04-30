package com.study.board.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostViewVO {
    private int postId;
    private String categoryName;
    private int fileCount;
    private String title;
    private String postContent;
    private String writer;
    private LocalDateTime writeDt;
    private LocalDateTime updateDt;
    private int viewCount;
}
