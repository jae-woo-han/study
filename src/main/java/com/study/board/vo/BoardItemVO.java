package com.study.board.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BoardItemVO {
    private int postId;
    private int postCount;
    private String categoryName;
    private int fileCount;
    private String title;
    private String writer;
    private LocalDateTime writeDt;
    private LocalDateTime updateDt;
    private int viewCount;
}
