package com.study.board.repository;

import com.study.board.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileRepository {
    FileVO selectFilesByPostId(int postId);
    int insertFileInfo(FileVO fileVO);
}
