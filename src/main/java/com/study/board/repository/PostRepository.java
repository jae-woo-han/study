package com.study.board.repository;

import com.study.board.vo.BoardItemVO;
import com.study.board.vo.PostSearchForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostRepository {

    List<BoardItemVO> selectBoardViewList(PostSearchForm postSearchForm);
}
