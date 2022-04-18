package com.study.board.vo;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Builder
public class PostSearchForm {
    private int postId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate toDt;
    private int categoryId;
    private String searchMessage;
    private int start;
}
