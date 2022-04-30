package com.study.board.vo;

import lombok.Data;

@Data
public class FileVO {
    private int fileSeq;
    private int postId;
    private String fileName;
    private String fileType;
    private String encodeFileName;
    private String directoryPath;
}
