package com.study.board.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostCreateForm {
    @Min(1)
    private int postId;
    @Min(1)
    private int categoryId;
    @NotEmpty
    @Size(max = 10,min = 3)
    private String title;
    @NotBlank
    private String postContent;
    @NotEmpty
    @Size(max=10,min=3)
    private String writer;
    @NotEmpty
    @Size(min=4)
    private String password;
    @NotEmpty
    private String passwordConfirm;
}
