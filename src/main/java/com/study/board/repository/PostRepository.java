package com.study.board.repository;

import com.study.board.vo.BoardItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostRepository {

    List<BoardItemVO> selectAll();
}
