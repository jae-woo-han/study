package com.study.board.repository;

import com.study.board.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileRepository {
    List<FileVO> selectFilesByPostId(int postId);
    FileVO selectFileByFileSeq(int fileSeq);
    int insertFileInfo(FileVO fileVO);
}
